# Source
Vào link để xem chi tiết có hình ảnh minh họa:

[Loda.me - Spring Boot - Xây dựng ứng dụng Reactive với Spring 5 Webflux
](https://loda.me/spring-boot-xay-dung-ung-dung-reactive-voi-spring-5-webflux-loda1557549671284)

# Content without images

### Tổng quan
Spring Webflux Framework là một phần của Spring 5 và cung cấp [**Reactive Programming**][reactive-programming] nhằm hỗ trợ cho việc xây dựng ứng dụng web.
Trong hướng dẫn này, chúng tôi sẽ đi một vài khái niệm để hiểu rõ về Spring Webflux, tiếp theo là xây dựng một ứng dụng Reactive Rest APIs đơn giản sử dụng Spring Webflux.
### Reactive Streams API
Đầu tiên chúng ta hiểu cơ chế hoạt động của **Reactive Streams API**.

**Reactive Stream API** được tạo bởi các kỹ sư từ Netflix, Pivotal, Lightbend, RedHat, Twitter, and Oracle và bây giờ là một phần của Java 9. Nó định nghĩa 4 interface:

`Publisher:`  Phát ra một chuỗi các sự kiện đến `subscriber` theo yêu cầu của người mà `subscriber` đến nó. Một `Publisher` có thể phục vụ nhiều `subscriber`. Interface này chỉ có một phương thức:

_Publisher.java_
```java
public interface Publisher<T>
{
    public void subscribe(Subscriber<? super T> s);
}
```

`Subscriber:` Nhận và xử lý sự kiện được phát ra bởi `Publisher`. Chú ý rằng không có gì xảy ra cho tới khi Subscription – nó được gọi là báo hiệu yêu cầu cho `Publisher`.

_Subscriber.java_
```java
public interface Subscriber<T>
{
    public void onSubscribe(Subscription s);
    public void onNext(T t);
    public void onError(Throwable t);
    public void onComplete();
}
```

`Subscription:` Định nghĩa mỗi quan hệ 1-1 giữa `Publisher` và `Subscriber`. Nó chỉ có thể được sử dụng bởi một `Subsriber` duy nhất và được sử dụng để báo hiệu yêu cầu (request) hoặc hủy (cancel) data.

_Subscription.java_
```java
public interface Subscription<T>
{
    public void request(long n);
    public void cancel();
}
```

`Processor:` Đại diện cho giai đoạn xử lý gồm cả `Publisher` và `Subscriber` đồng thời tuân thủ nguyên tắc của cả 2.

_Processor.java_
```java
public interface Processor<T, R> extends Subscriber<T>, Publisher<R>
{
}
```

Bản chất, một `Subscriber` tạo một ` Subscription` tới `Publisher`, sau đó `Publisher` gửi một sự kiện cho `Subsriber` với một luồng các phần tử.

![Subscriber-Publisher-Subscription](../../images/loda1557549671284/2.jpg)

### Spring WebFlux
**Spring Webflux** là một phiên bản song song với **Spring MVC** và hỗ trợ non-blocking reactive streams. Nó hỗ trợ khái niệm **back pressure** và sử dụng Server Netty để run hệ thống reactive. Nếu bạn đã quen thuộc với style **Spring MVC** thì bạn cũng dễ dàng làm việc với **Spring Webflux**.

Spring webflux sử dụng project reactor ( Thư viện implements phỗ biến nhất) như một thư viện reactive, vì thế nó cung cấp 2 kiểu `Publisher`:

`Mono`: Phát ra 0 hoặc 1 phần tử.

`Flux`  : Phát ra 0..N phần tử.

### Dependencies
Dươi đây là dependency của Webflux, nó đã kéo theo các dependencies khác gồm:

1. spring-boot và spring boot-starter
2. spring-webflux framework
3. reactor-core

```
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
    <version>2.0.5.RELEASE</version>
</dependency>
```

### Demo String Boot WebFlux
Chúng tôi sẽ xây dựng một ứng dụng đơn giản sử dụng Spring Webflux.

- Để đơn giản chúng tôi tạo một đối tượng đơn giản là `Employee` với 2 thuộc tính đơn giản
- Tạo một Rest APIS cho việc query danh sách `Employees` sử dụng @RestController. 
- Cuối cùng là tạo 1 DB đơn giản và hỗ trợ Reactive bằng việc trả về kiểu `Flux` mà Webflux cung cấp. Các bạn có thể sử dụng DB lữu trữ khác hỗ trợ Reative như là **MongoDB**.

_pom.xml_
```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
        <version>2.0.5.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <version>2.0.5.RELEASE</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

_EmployeeApplication.class_
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class EmployeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }
}
```
_Employee.class_
```java
public class Employee {
    private String id;
    private String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
_EmployeeService.class_
```java
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Flux<Employee> findAll();
}

```
_EmployeeServiceImpl.class_
```java
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Flux<Employee> findAll() {
        return Flux.just(new Employee("29750","AtomPtit") , new Employee("18273", "HungCD"));
    }
}
```
_EmployeeController.class_
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    private Flux<Employee> getAllEmpployees() {
       return employeeService.findAll();
    }
}

```

OUTPUT:
```java
[
    {
        "id": "29750",
        "name": "AtomPtit"
    },
    {
        "id": "18273",
        "name": "HungCD"
    }
]
```

### Kết luận
Cả `Spring MVC` và Spring `Webflux` để hỗ trợ kiến trúc Client-Server nhưng điểm khác nhau chính là mô hình `concurrency` và hành động mặc định trong tính chất non-blocking và threads.

Trong Spring MVC, nó mặc định rằng ứng dụng có thể bị block tại thread hiện tại, trong khi webflux thì mặc định threads là non-blocking .

Reactive và non-blocking nhìn chung thì không làm cho ứng dụng chạy nhanh hơn. Lợi ích mà nó được kỳ vọng là mở rộng ứng dụng với số luồng nhỏ và yêu cầu ít bộ nhớ hơn. Nó làm cho các ứng dụng trở nên linh hoạt hơn khi tải.

Cuối cùng, source code hoàn chỉnh được sử dụng trong hướng dẫn này có sẵn [trên Github][link-github]
<a class="btn btn-icon btn-github mr-1" target="_blank" href="https://github.com/loda-kun/spring-boot-learning">
<i class="fab fa-github"></i>
</a>
[reactive-programming]: https://loda.me/gioi-thieu-reactive-programming-voi-reactor-loda1556032486705
[link-github]: https://github.com/loda-kun/spring-boot-learning