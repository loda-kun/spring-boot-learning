# Source
Vào link để xem chi tiết có hình ảnh minh họa:

[Loda.me - [Spring JPA] Hướng dẫn Auditing với Hibernate + JPA][loda-link]

[loda-link]: https://loda.me/spring-jpa-huong-dan-auditing-voi-hibernate-jpa-loda1559899292669

# Content without images

### Giới thiệu

Trước khi đi vào khái niệm niệm **Auditing**, bạn cần biết:

1. [Hibernate là gì?][link-hibernate]
2. [Spring JPA][link-jpa]

Ở trong **ORM**, **Auditing** có nghĩa là việc theo dõi và logging các event liên quan tới `Entity` như insert(persist), update hay delete, thậm chí là thay đổi version của `Entity`.

Trên thực tế, đôi khi Auditing đơn giản chỉ là việc cập nhật giá trị `created_at` và `updated_at` của đối tượng trong database.

![spring-profiles](../../images/loda1559899292669/2.png)

Trong bài hôm nay chúng ta sẽ tìm hiểu cách cập nhật các giá trị này một cách tự động với `Spring JPA`

### @EnableJpaAuditing

Để kích hoạt tính năng Auditing trong Spring Boot, bạn sử dụng Annotation `@EnableJpaAuditing`

```java

@SpringBootApplication
// Bạn phải kích hoạt chức năng Auditing bằng annotation @EnableJpaAuditing
@EnableJpaAuditing
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

### @EntityListeners

Bạn có biết tới khái niệm `@EventListener`, nếu chưa, hãy xem ngay tại đây:

1. [Xử lý sự kiện với @EventListener][link-event-listener]

Khi chúng ta insert hay update một `Entity` xuống database bằng JPA, nó sẽ cũng sinh ra các sự kiện và đây là cơ sở để chúng ta auditing.

Khác với `@EventListener` thì chúng ta có riêng một annotation chỉ để lắng nghe sự kiện liên quan tới `Entity` là `@EntityListeners`.

**Spring JPA** tự động lắng nghe các sự kiện này và xử lý giúp chúng ta bằng class `AuditingEntityListener`

```java
@Data
@Entity
@Table(name = "app_params")
// Bạn phải đánh dấu class bởi @EntityListeners(AuditingEntityListener.class)
// Đây là Một đối tượng Listener, lắng nghe sự kiện insert hoặc update của đối tượng
// Để từ đó tự động cập nhật các trường @CreatedDate và @LastModifiedDate
@EntityListeners(AuditingEntityListener.class)
public class AppParams {
}
```

### @CreatedDate & @LastModifiedDate

`@CreatedDate` và `@LastModifiedDate` được chú thích trên trường `Date` của `Entity`

Khi `Entity` insert vào database, JPA sẽ cập nhật giá trị của trường có chú thích bởi `@CreatedDate`

Khi `Entity` insert hoặc update vào database, JPA sẽ cập nhật giá trị của trường có chú thích bởi `@LastModifiedDate`.

```java
@EntityListeners(AuditingEntityListener.class)
public class AppParams {
    /*
    đánh dấu trường Date với @CreatedDate
    Nếu đối tượng được insert vào database lần đầu -> nó sẽ tự động lấy thời điểm đó đưa vào createdAt
     */
    @CreatedDate
    private Date createdAt;

    /*
    đánh dấu trường Date với @LastModifiedDate
    Nếu đối tượng được insert vào database lần đầu -> nó sẽ tự động lấy thời điểm đó đưa vào updatedAt
    Nếu đối tượng được update vào database lần tiếp theo -> nó sẽ tự động lấy thời điểm đó cập nhật vào updatedAt
     */
    @LastModifiedDate
    private Date updatedAt;
}

```

### Ví dụ

#### Cài đặt

Nhớ thêm `spring-boot-starter-data-jpa` vào dependencies của bạn.

_pom.xml_
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.5.RELEASE</version>
        <relativePath /> <!-- lookup parent from repository -->
    </parent>
    <groupId>me.loda.spring</groupId>
    <artifactId>spring-boot-learning</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-boot-learning</name>
    <description>Everything about Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>

        <!--spring mvc, rest-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--spring jpa-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- mysql connector -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        </plugins>
    </build>

</project>
```

_application.properties_

```java
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/lodadb?useSSL=false
spring.datasource.username=root
spring.datasource.password=


## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update

logging.level.org.hibernate = ERROR
```

#### Tạo Database

```sql
CREATE DATABASE lodadb;
use lodadb;

CREATE TABLE `app_params` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(45) NOT NULL,
  `param_value` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

#### Tạo Entity 

Tạo ra đối tượng `Entity` tương ứng cho table `app_params`.

Chúng ta sử dụng Auditing để tự động cập nhật 2 giá trị `created_at` và `updated_at`

```java
@Builder
@Data
@Entity
@Table(name = "app_params")
// Bạn phải đánh dấu class bởi @EntityListeners(AuditingEntityListener.class)
// Đây là Một đối tượng Listener, lắng nghe sự kiện insert hoặc update của đối tượng
// Để từ đó tự động cập nhật các trường @CreatedDate và @LastModifiedDate
@EntityListeners(AuditingEntityListener.class)
public class AppParams {
    private static final long serialVersionUID = 2783421558989997612L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String paramName;
    private String paramValue;

    /*
    đánh dấu trường Date với @CreatedDate
    Nếu đối tượng được insert vào database lần đầu -> nó sẽ tự động lấy thời điểm đó đưa vào createdAt
     */
    @CreatedDate
    private Date createdAt;

    /*
    đánh dấu trường Date với @LastModifiedDate
    Nếu đối tượng được insert vào database lần đầu -> nó sẽ tự động lấy thời điểm đó đưa vào updatedAt
    Nếu đối tượng được update vào database lần tiếp theo -> nó sẽ tự động lấy thời điểm đó cập nhật vào updatedAt
     */
    @LastModifiedDate
    private Date updatedAt;
}

```

Tạo ra Repository để thao tác với Database.

_AppParamsRepository.java_

```java
public interface AppParamsRepository extends JpaRepository<AppParams, Long> {
}
```

#### Chạy thử

_App.java_

```java

@SpringBootApplication
// Bạn phải kích hoạt chức năng Auditing bằng annotation @EnableJpaAuditing
@EnableJpaAuditing
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    AppParamsRepository appParamsRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            AppParams appParams = AppParams.builder()
                                           .paramName("Loda")
                                           .paramValue("handsome - https://loda.me")
                                           .build();

            System.out.println("Đối tượng Param trước khi insert: " + appParams);
            appParamsRepository.save(appParams);
            System.out.println("Đối tượng Param sau khi insert: " + appParams);

            System.out.println("In ra tất cả bản ghi trong DB:");
            System.out.println(appParamsRepository.findAll());
        };
    }
}

```

OUTPUT:

```
Đối tượng Param trước khi insert:
    AppParams(id=null, paramName=Loda, paramValue=handsome - https://loda.me, createdAt=null, updatedAt=null)

Đối tượng Param sau khi insert:
    AppParams(id=1, paramName=Loda, paramValue=handsome - https://loda.me, createdAt=Fri Jun 07 16:21:07 ICT 2019, updatedAt=Fri Jun 07 16:21:07 ICT 2019)

In ra tất cả bản ghi trong DB:
    [AppParams(id=1, paramName=Loda, paramValue=handsome - https://loda.me, createdAt=2019-06-07 16:21:07.0, updatedAt=2019-06-07 16:21:07.0)]
```

Bạn sẽ thấy là giá trị `created_at` và `updated_at` đã được tự động cập nhật.

### Kết

**Auditing** thì giúp chúng ta trong việc tracking dữ liệu, còn **Spring JPA** lại giúp chúng ta Auditing hết sức dễ dàng.

Việc Audit không chỉ dừng ở update thời gian tạo và update, mà còn có thể lưu tên người đã gây ra sự thay đổi đó. Tôi sẽ đề cập ở bài viết sau.

Và như mọi khi, [toàn bộ code đều được up lên Github][link-github].
<a class="btn btn-icon btn-github mr-1" target="_blank" href="https://github.com/loda-kun/spring-boot-learning">
<i class="fab fa-github"></i>
</a>

[link-github]: https://github.com/loda-kun/spring-boot-learning
[link-hibernate]: https://loda.me/hibernate-la-gi-loda1554623701594
[link-jpa]: https://loda.me/spring-boot-11-huong-dan-spring-boot-jpa-my-sql-loda1558687596060
[link-event-listener]: https://loda.me/spring-boot-xu-ly-su-kien-voi-event-listener-async-loda1559286387703

