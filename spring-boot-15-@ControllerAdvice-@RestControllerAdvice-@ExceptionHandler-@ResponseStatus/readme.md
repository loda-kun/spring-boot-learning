# Source
Vào link để xem chi tiết có hình ảnh minh họa:

[Loda.me - 「Spring Boot #15」 Exception Handling @ExceptionHandler + @RestControllerAdvice / @ControllerAdvice + @ResponseStatus][loda-link]

[loda-link]: https://loda.me/spring-boot-15-exception-handling-exception-handler-rest-controller-advice-controller-advice-response-status-loda1558838525127

# Content without images

### Giới thiệu

[Trong bài trước][link-spring-boot-14] chúng ta đã biết cách làm một Rest Api Server với **Spring Boot**.

1. [「Spring Boot #14」 Restful API + @RestController + @PathVariable + @RequestBody][link-spring-boot-14]

Tuy nhiên có một số vấn đề mà chúng ta kiểm soát. Ví dụ như nếu người dùng request lên một `id` không hề tồn tại thì sao?

![spring-boot-rest-api](../../images/loda1558838525127/3.jpg)

Trong bài viết này, chúng ta sẽ tìm hiểu cách xử lý Exception trong **Spring Boot**

### @RestControllerAdvice & @ControllerAdvice + @ExceptionHandler

`@RestControllerAdvice` là một Annotation gắn trên Class. Có tác dụng xen vào quá trình xử lý của các
`@RestController`. Tương tự với `@ControllerAdvice`

`@RestControllerAdvice` thường được kết hợp với `@ExceptionHandler` để cắt ngang quá trình xử lý của Controller, và xử lý các ngoại lệ xảy ra.

```java
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage TodoException(Exception ex,  WebRequest request) {
        return new ErrorMessage(10100, "Đối tượng không tồn tại");
    }
}
```

Hiểu đơn giản là Controller đang hoạt động bình thường, chẳng may có một Exception được ném ra, thì thay vì báo lỗi hệ thống, thì nó sẽ được thằng `@RestControllerAdvice` và `@ExceptionHandler` đón lấy và xử lý. Sau đó trả về cho người dùng thông tin hữu ích hơn.

### @ResponseStatus
`@ResponseStatus` là một cách định nghĩa Http Status trả về cho người dùng. 

Nếu bạn không muốn sử dụng `ResponseEntity` thì có thể dùng `@ResponseStatus` đánh dấu trên `Object` trả về.

### Demo

#### Cài đặt
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

![spring-boot-rest-api](../../images/loda1558838525127/2.jpg)

#### Tạo model

Sử dụng [Lombok][link-lombok] cho tiện nha các bạn.

_Todo.java_
```java
@Data
@AllArgsConstructor
public class Todo {
    private String title;
    private String detail;
}

```

Tạo ra class `ErrorMessage` để chứa thông tin trả về cho Client.

_ErrorMessage.java_
```java
@Data
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private String message;
}
```

#### Tạo Controller

_RestApiController.java_
```java

/**
 * Lưu ý, @RequestMapping ở class, sẽ tác động tới
 * tất cả các RequestMapping ở bên trong nó.
 * <p>
 * Mọi Request ở trong method sẽ được gắn thêm prefix /api/v1
 */
@RestController
@RequestMapping("/api/v1")
public class RestApiController {

    private List<Todo> todoList;

    // bạn còn nhớ @PostConstruct dùng để làm gì chứ?
    // nếu không nhớ, hãy coi lại bài viết Spring Boot #3 nhé
    @PostConstruct
    public void init() {
        todoList = IntStream.range(0, 10)
                 .mapToObj(i -> new Todo("title-" + i, "detail-" + i))
                 .collect(Collectors.toList());
    }

    /*
    phần path URL bạn muốn lấy thông tin sẽ để trong ngoặc kép {}
     */
    @GetMapping("/todo/{todoId}")
    public Todo getTodo(@PathVariable(name = "todoId") Integer todoId) {
        // @PathVariable lấy ra thông tin trong URL
        // dựa vào tên của thuộc tính đã định nghĩa trong ngoặc kép /todo/{todoId}
        return todoList.get(todoId);
    }
}

```

#### Tạo Exception Handler

_ApiExceptionHandler.java_

```java
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAllException(Exception ex, WebRequest request) {
        // quá trình kiểm soat lỗi diễn ra ở đây
        return new ErrorMessage(10000, ex.getLocalizedMessage());
    }

    /**
     * IndexOutOfBoundsException sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage TodoException(Exception ex, WebRequest request) {
        return new ErrorMessage(10100, "Đối tượng không tồn tại");
    }
}

```

#### Chạy thử

_App.java_

```java
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

Gửi request tới địa chỉ:

```
GET http://localhost:8080/api/v1/todo/11
```

Ở đây, đối tượng 11 không tồn tại trong danh sách, chúng ta sẽ trả về lỗi cho phía Client.

![spring-boot-rest-api](../../images/loda1558838525127/4.jpg)


### Kết

Như mọi khi, [toàn bộ code tham khảo tại Github][link-github]
<a class="btn btn-icon btn-github mr-1" target="_blank" href="https://github.com/loda-kun/spring-boot-learning">
<i class="fab fa-github"></i>
</a>

[link-lombok]: https://loda.me/general-huong-dan-su-dung-lombok-giup-code-java-nhanh-hon-69-loda1552789752787/
[link-github]: https://github.com/loda-kun/spring-boot-learning
[link-spring-boot-14]: https://loda.me/spring-boot-14-restful-api-rest-controller-path-variable-request-body-loda1558775921707