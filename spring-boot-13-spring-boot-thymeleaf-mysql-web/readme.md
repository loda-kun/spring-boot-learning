# Source
Vào link để xem chi tiết có hình ảnh minh họa:

[Loda.me - 「Spring Boot #13 Special」 Chi tiết Spring Boot + Thymeleaf + MySQL + i18n + Web Demo](https://loda.me/spring-boot-13-special-chi-tiet-spring-boot-thymeleaf-my-sql-i18n-web-demo-loda1558758475731)

# Content without images

### Giới thiệu

Trong loạt series về Spring Boot này, chúng ta đã đi qua hết tất cả các kiến thức căn bản và cần thiết.

1. [「Spring Boot #1」Hướng dẫn @Component và @Autowired][link-spring-boot-1]
2. [「Spring Boot #2」@Autowired - @Primary - @Qualifier][link-spring-boot-2]
3. [「Spring Boot #3」Spring Bean Life Cycle + @PostConstruct và @PreDestroy][link-spring-boot-3]
4. [「Spring Boot #4」@Component vs @Service vs @Repository][link-spring-boot-4]
5. [「Spring Boot #5」Component Scan là gì?][link-spring-boot-5]
6. [「Spring Boot #6」@Configuration và @Bean][link-spring-boot-6]
7. [「Spring Boot #7」Spring Boot Application Config và @Value][link-spring-boot-7]
8. [「Spring Boot #8」Tạo Web Helloworld với @Controller][link-spring-boot-8]
9. [「Spring Boot #9」Giải thích cách Thymeleaf vận hành + Expression + Demo Full][link-spring-boot-9]
10. [「Spring Boot #10」@RequestMapping + @PostMapping + @ModelAttribute + @RequestParam + Web To-Do với Thymeleaf][link-spring-boot-10]
11. [「Spring Boot #11」Hướng dẫn Spring Boot JPA + MySQL][link-spring-boot-11]
12. [「Spring Boot #12」Spring JPA Method + @Query][link-spring-boot-12]


Hôm nay, chúng ta sẽ vận dụng toàn bộ kiến thức đã học để tạo ra website quản lý công việc bằng Spring Boot + Thymeleaf + MySQL.

### Cài đặt

Chúng ta sẽ các dependencies sau:

1. spring-boot-starter-web
2. lombok
3. spring-boot-starter-thymeleaf
4. spring-boot-starter-data-jpa
5. mysql-connector-java

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

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
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

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
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

Cấu trúc thư mục:

![spring-thymleaf-mysql](../../images/loda1558758475731/2.jpg)
![sspring-thymleaf-mysq](../../images/loda1558758475731/2_2.jpg)

### Tạo Database

_script.sql_

```sql
CREATE SCHEMA IF NOT EXISTS `todo_db` DEFAULT CHARACTER SET utf8mb4 ;

CREATE TABLE IF NOT EXISTS `todo_db`.`todo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `detail` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
```

Thêm 1 record vào DB

```sql
INSERT INTO `todo_db`.`todo` (`title`, `detail`) VALUES ('Làm bài tập', 'Hoàn thiện bài viết Spring Boot #13');
```

Xem thử kết quả:

![sspring-thymleaf-mysq](../../images/loda1558758475731/3.jpg)

### Cấu hình ứng dụng

Cấu hình là phần cực kì quan trọng rồi, chúng ta phải cung cấp cho **Spring Boot** các thông tin về Database và Thymeleaf.

Ngoài ra, tùy chỉnh một số thông tin để giúp chúng ta lập trình đơn giản hơn.

_application.properties_

```java
#Chạy ứng dụng trên port 8085
server.port=8085

# Bỏ tính năng cache của thymeleaf để lập trình cho nhanh
spring.thymeleaf.cache=false

# Các message tĩnh sẽ được lưu tại thư mục i18n
spring.messages.basename=i18n/messages


# Bỏ properties này đi khi deploy
# Nó có tác dụng cố định ngôn ngữ hiện tại chỉ là Tiếng Việt
spring.mvc.locale-resolver=fixed

# Mặc định ngôn ngữ là tiếng việt
spring.mvc.locale=vi_VN
# Đổi thành tiếng anh bằng cách bỏ comment ở dứoi
#spring.mvc.locale=en_US

spring.datasource.url=jdbc:mysql://localhost:3306/todo_db?useSSL=false
spring.datasource.username=root
spring.datasource.password=root


## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update
```

### Tạo Model

Tạo model `Todo` liên kết tới bảng `todo` trong Database.

Phần này chúng ta sử dụng [Lombok][link-lombok] và [Hibernate][link-hibernate]
_Todo.java_

```java
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String detail;
}

```

Ngoài ra, chúng ta tạo thêm một đối tượng là `TodoValidator`, có trách nhiệm kiểm tra xem một object `Todo` là hợp lệ hay không.

```java
import org.thymeleaf.util.StringUtils;

/*
Đối tượng này dùng để kiểm tra xem một Object Todo có hợp lệ không
 */
public class TodoValidator {

    /**
     * Kiểm tra một object Todo có hợp lệ không
     * @param todo
     * @return
     */
    public boolean isValid(Todo todo) {
        return Optional.ofNullable(todo)
                       .filter(t -> !StringUtils.isEmpty(t.getTitle())) // Kiểm tra title khác rỗng
                       .filter(t -> !StringUtils.isEmpty(t.getDetail())) // Kiểm tra detail khác rỗng
                       .isPresent(); // Trả về true nếu hợp lệ, ngược lại false
    }
}


```

Vậy là xong phần chuẩn bị `Model`.


### TodoConfig

Trong ứng dụng của mình, tôi muốn tự tạo ra Bean `TodoValidator`.

Đây là lúc sử dụng `@Configuration` và `@Bean` đã học tại bài [Spring Boot #6][link-spring-boot-6]

_config/TodoConfig.java_

```java
@Configuration
public class TodoConfig {
    /**
     * Tạo ra Bean TodoValidator để sử dụng sau này
     * @return
     */
    @Bean
    public TodoValidator validator() {
        return new TodoValidator();
    }
}

```

### Tầng Repository

Tầng Repository, chịu trách nhiệm giao tiếp với Database. Chúng ta sử dụng **Spring JPA**.

_repository/TodoRepository.java_

```java
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
```

### Tầng Service

Tầng Service, chị trách nhiệm thực hiện các xử lý logic, business, hỗ trợ cho tầng Controller.

_service/TodoService.java_

```java
@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoValidator validator;

    /**
     * Lấy ra danh sách List<Todo>
     *
     * @param limit - Giới hạn số lượng lấy ra
     *
     * @return Trả về danh sách List<Todo> dựa theo limit, nếu limit == null thì trả findAll()
     */
    public List<Todo> findAll(Integer limit) {
        return Optional.ofNullable(limit)
                       .map(value -> todoRepository.findAll(PageRequest.of(0, value)).getContent())
                       .orElseGet(() -> todoRepository.findAll());
    }

    /**
     * Thêm một Todo mới vào danh sách công việc cần làm
     *
     * @return Trả về đối tượng Todo sau khi lưu vào DB, trả về null nếu không thành công
     */
    public Todo add(Todo todo) {
        if (validator.isValid(todo)) {
            return todoRepository.save(todo);
        }
        return null;
    }
}

```

### Tầng Controller

Tầng Controller, nơi đón nhận các request từ phía người dùng, và chuyển tiếp xử lý xuống tầng Service.

_controller/TodoController.java_

```java
@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    /*
    @RequestParam dùng để đánh dấu một biến là request param trong request gửi lên server.
    Nó sẽ gán dữ liệu của param-name tương ứng vào biến
     */
    @GetMapping("/listTodo")
    public String index(Model model, @RequestParam(value = "limit", required = false) Integer limit) {
        // Trả về đối tượng todoList.
        model.addAttribute("todoList", todoService.findAll(limit));
        // Trả về template "listTodo.html"
        return "listTodo";
    }

    @GetMapping("/addTodo")
    public String addTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "addTodo";
    }

    /*
    @ModelAttribute đánh dấu đối tượng Todo được gửi lên bởi Form Request
     */
    @PostMapping("/addTodo")
    public String addTodo(@ModelAttribute Todo todo) {
        return Optional.ofNullable(todoService.add(todo))
                       .map(t -> "success") // Trả về success nếu save thành công
                       .orElse("failed"); // Trả về failed nếu không thành công

    }
}

```

### Templates

Tầng Controller đã trả về templates, nhiệm vụ tiếp theo là sử dụng Template Engine để xử lý các templates này và trả về webpage cho người dùng. 

Template Engine chúng ta sử dụng sẽ là **Thymeleaf**, đã học tại các bài Spring Boot [#8][link-spring-boot-8], [#9][link-spring-boot-9], [#10][link-spring-boot-10].

_index.html_

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8" />
  <title>Loda To-do</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

  <!--css-->
  <link th:href="@{/css/bootstrap.css}" rel="stylesheet" />
  <link th:href="@{/css/main.css}" rel="stylesheet" />

  <!--js-->
  <script th:src="@{/js/bootstrap.js}"></script>
</head>
<body>
<h1 th:text="#{loda.message.hello}"></h1>

<a th:href="@{/listTodo}" th:text="#{loda.value.viewListTodo}" class="btn btn-primary"></a>
<a th:href="@{/addTodo}" th:text="#{loda.value.addTodo}" class="btn btn-success"></a>
</body>

</html>
```

_listTodo.html_

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8" />
  <title>Loda To-do</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

  <!--css-->
  <link th:href="@{/css/bootstrap.css}" rel="stylesheet" />
  <link th:href="@{/css/main.css}" rel="stylesheet" />

  <!--js-->
  <script th:src="@{/js/bootstrap.js}"></script>
</head>
<body>
<h1 th:text="#{loda.value.listTodo} + ':'"></h1>


<ul>
  <!--Duyệt qua toàn bộ phần tử trong biến "todoList"-->
  <li th:each="todo : ${todoList}">
    <!--Với mỗi phần tử, lấy ra title và detail-->
    <span th:text="*{todo.getTitle()}"></span> : <span th:text="*{todo.getDetail()}"></span>
  </li>
</ul>

<a th:href="@{/addTodo}" th:text="#{loda.value.addTodo}" class="btn btn-success"></a>
</body>

</html>
```

_addTodo.html_

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8" />
  <title>Loda To-do</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

  <!--css-->
  <link th:href="@{/css/bootstrap.css}" rel="stylesheet" />
  <link th:href="@{/css/main.css}" rel="stylesheet" />

  <!--js-->
  <script th:src="@{/js/bootstrap.js}"></script>

</head>
<body>
<h1>To-do</h1>

<form th:action="@{/addTodo}" th:object="${todo}" method="post">
  <p>title: <input type="text" th:field="*{title}" /></p>
  <p>detail: <input type="text" th:field="*{detail}" /></p>
  <p><button type="submit" class="btn btn-success">Add</button></p>
</form>
</body>

</html>
```

_success.html_

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8" />
  <title>Loda To-do</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

  <link th:href="@{/css/bootstrap.css}" rel="stylesheet" />
  <link th:href="@{/css/main.css}" rel="stylesheet" />

</head>
<body>
<h1>To-do</h1>
<h1 style="color: green" th:text="#{loda.message.success}"></h1>

<a th:href="@{/listTodo}" th:text="#{loda.value.viewListTodo}" class="btn btn-primary"></a>

</body>

</html>
```

_failed.html_

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8" />
  <title>Loda To-do</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

  <link th:href="@{/css/bootstrap.css}" rel="stylesheet" />
  <link th:href="@{/css/main.css}" rel="stylesheet" />

</head>
<body>
<h1>To-do</h1>
<h1 style="color: red" th:text="#{loda.message.failed}"></h1>

<a th:href="@{/listTodo}" th:text="#{loda.value.viewListTodo}" class="btn btn-primary"></a>

</body>

</html>
```

### i18n

Trong các template, tôi có sử dụng các message tĩnh, những message này hỗ trợ đa ngôn ngữ. 

Chúng ta định nghĩa các message này tại thư mục `i18n`.

_i18n/messages_vi.properties_

```java
loda.message.hello=Welcome to TodoApp
loda.message.success=Thêm Todo thành công!
loda.message.failed=Thêm Todo không thành công!

loda.value.addTodo=Thêm công việc
loda.value.viewListTodo=Xem danh sách công việc
loda.value.listTodo=Danh sách công việc
```

_i18n/messages_en.properties_

```java
loda.message.hello=Welcome to TodoApp
loda.message.success=Add To-do Successfully!
loda.message.failed=Add To-do Failed!

loda.value.addTodo=Add To-do
loda.value.viewListTodo=View To-do list
loda.value.listTodo=To-do list
```

### Chạy thử ứng dụng

Chạy ứng dụng: 

_App.java_

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

Truy cập địa chỉ: `http://localhost:8085/`

![sspring-thymleaf-mysq](../../images/loda1558758475731/4.jpg)

Vì chúng ta cấu hình `Locale` là `vi`, nên ngôn ngữ đều hiện Tiếng Việt, rất tuyệt :3 

Bấm vào **Xem danh sách công việc** để tới `/listTodo`

![sspring-thymleaf-mysq](../../images/loda1558758475731/5.jpg)

Vì chúng ta đã insert 1 bản ghi vào Database từ trước, nên ở đây nó hiện ra 1 việc cần làm.

Bấm vào **Thêm công việc** để tới `/addTodo`

![sspring-thymleaf-mysq](../../images/loda1558758475731/7.jpg)

Bấm **Add** để lưu thông tin vào Database.

![sspring-thymleaf-mysq](../../images/loda1558758475731/8.jpg)

Vậy là giờ chúng ta có 2 công việc :3

![sspring-thymleaf-mysq](../../images/loda1558758475731/9.jpg)

Bây giờ giả sử dụng ta gửi lên request tạo ra một Todo không hợp lệ.

`TodoValidator` sẽ trả về null -> thêm thất bại

![sspring-thymleaf-mysq](../../images/loda1558758475731/10.jpg)

### Kết

Vậy là chúng ta đã đi được nửa Series **Spring Boot**. 

Trong các phần tới, chúng ta sẽ tìm hiểu về cách làm RestAPI với **Spring Boot**, đây mới là phần mạnh mẽ của nhất.


Như mọi khi, [toàn bộ code tham khảo tại Github][link-github]
<a class="btn btn-icon btn-github mr-1" target="_blank" href="https://github.com/loda-kun/spring-boot-learning">
<i class="fab fa-github"></i>
</a>








[link-lombok]: https://loda.me/general-huong-dan-su-dung-lombok-giup-code-java-nhanh-hon-69-loda1552789752787/
[link-hibernate]: https://loda.me/hibernate-la-gi-loda1554623701594

[link-spring-boot-1]: https://loda.me/spring-boot-1-huong-dan-component-va-autowired-loda1557412317602
[link-spring-boot-2]: https://loda.me/spring-boot-2-autowired-primary-qualifier-loda1557561089057
[link-spring-boot-3]: https://loda.me/spring-boot-3-spring-bean-life-cycle-post-construct-va-pre-destroy-loda1557583753982
[link-spring-boot-4]: https://loda.me/spring-boot-4-component-vs-service-vs-repository-loda1557627097246
[link-spring-boot-5]: https://loda.me/spring-boot-5-component-scan-la-gi-loda1557673850320
[link-spring-boot-6]: https://loda.me/spring-boot-6-configuration-va-bean-loda1557885506910
[link-spring-boot-7]: https://loda.me/spring-boot-7-spring-boot-application-config-va-value-loda1558171356103
[link-spring-boot-8]: https://loda.me/spring-boot-8-tao-web-helloworld-voi-controller-loda1558189401113
[link-spring-boot-9]: https://loda.me/spring-boot-9-huong-dan-chi-tiet-lam-web-voi-thymeleaf-demo-full-loda1558661736676
[link-spring-boot-10]: https://loda.me/spring-boot-10-request-mapping-post-mapping-model-attribute-request-param-web-to-do-voi-thymeleaf-loda1558661736676
[link-spring-boot-11]: https://loda.me/spring-boot-11-huong-dan-spring-boot-jpa-my-sql-loda1558687596060
[link-spring-boot-12]: https://loda.me/spring-boot-12-spring-jpa-method-query-loda1558746200832
[link-github]: https://github.com/loda-kun/spring-boot-learning
