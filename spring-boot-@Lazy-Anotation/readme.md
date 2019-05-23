# Source
Vào link để xem chi tiết có hình ảnh minh họa:

[Loda.me - 「Spring Boot」Annotation @Lazy trong Spring Boot](https://loda.me/spring-boot-annotation-lazy-trong-spring-boot-loda1558283720495)

# Content without images

### Tổng quan
Như mặc định, Spring sẽ tạo tất cả các `singleton Bean` trong quá trình startup `Application Context`. Tuy nhiên, có những trường hợp chúng ta chưa dùng đến `Bean` khi mới startup `Application Context`, mà khi nào yêu cầu thì nó sẽ được tạo ra. Để làm được điều đó, Spring cho ra đời annotation `@Lazy.

Trong bài viết này, chúng tôi sẽ thảo luận về annotation `@Lazy` kết hợp với các annotation khác.

### @Lazy và @Configuration

Đầu tiên, chúng tôi tạo ra các `Bean` đơn giản như sau:

_FirstBean.class_
```java
public class FirstBean {
    public FirstBean() {
        System.out.println("Bean FirstBean đã được khởi tạo!");
    }
}
```

_SecondBean.class_
```java
public class SecondBean {
    public SecondBean() {
        System.out.println("Bean SecondBean đã được khởi tạo!");
    }
}
```
_ApplicationConfig.class_
```java
@Lazy
@Configuration
public class ApplicationConfig {

    @Bean
    public FirstBean firstBean() {
        return new FirstBean();
    }

    @Bean
    public SecondBean secondBean() {
        return new SecondBean();
    }
}
```
_ExampleApplication.class_
```java
@SpringBootApplication
public class ExampleApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExampleApplication.class);
        System.out.println("ApplicationContext đã khởi tạo!");
        FirstBean firstBean = context.getBean(FirstBean.class);
        SecondBean secondBean = context.getBean(SecondBean.class);
        context.close();
    }
}
```

Khi chúng ta đặt annotation `@Lazy` trong class `@Configuration` , nó chỉ ra rằng tất các phương thức với annotation `@Bean` sẽ tạm thời chưa được khởi tạo khi `ApplicationContext` startup. Sau khi start xong, thì chúng tôi yêu cầu getBean() thì chúng mới bắt đầu được khởi tạo, điều này được chứng minh ở output khi run chương trình:

```java
/* OUPUT:
ApplicationContext đã khởi tạo!
Bean FirstBean đã được khởi tạo!
Bean SecondBean đã được khởi tạo!

*/
```

### @Lazy và @Bean
Để áp dụng `Bean` được chỉ định riêng, đơn giản chỉ cần đặt annotation `@Lazy` vào `Bean` được yêu cầu. Khi đó chúng tôi thay đổi code class `_ApplicationConfig` như sau:

__ApplicationConfig.class_
```java
@Configuration
public class ApplicationConfig {

    @Lazy
    @Bean
    public FirstBean firstBean() {
        return new FirstBean();
    }

    @Bean
    public SecondBean secondBean() {
        return new SecondBean();
    }
}

```
Khi đó `Bean` SecondBean sẽ được khởi tạo đồng thời khi hệ thống running, còn `Bean` FirstBean sẽ được khởi tạo sau khi được yêu cầu.
```java
/* OUTPUT
Bean SecondBean đã được khởi tạo!
ApplicationContext đã khởi tạo!
Bean FirstBean đã được khởi tạo!
*/
```

### @Lazy và @Component
 Thay vì tạo `Bean` trong class `Config`, chúng tôi cũng có thể khởi tạo Class sử dụng annotation `@Component`. Khi đó trường hợp này tương tự với trường hợp kết hợp `@Lazy` và `@Bean`.
_FirstBean.class_
```java
@Lazy
@Component
public class FirstBean {
    public FirstBean() {
        System.out.println("Bean FirstBean đã được khởi tạo!");
    }
}
```

### @Lazy và @Autowired
Chú ý rằng trong trường hợp này `@Lazy` được đặt cả ở `@Component` và `@Autowired`.
_ServiceBean.class_
```java
public class ServiceBean {

    @Lazy
    @Autowired
    private FirstBean firstBean;

    public FirstBean getFirstBean() {
        return firstBean;
    }
}
```
Khi đó `Bean` FirstBean sẽ được khởi tạo khi chúng tôi gọi hàm `getFirstBean()`.
### Kết luận
Với những ví dụ đơn giản trên, các bạn cũng đã hiểu cơ bản về annotation này. [Code cho bài hướng dẫn này có sẵn trên Github][link-github].

### Tham Khảo

1. [「Spring Boot #1」Hướng dẫn @Component và @Autowired][link-spring-boot-1]
2. [「Spring Boot #2」@Autowired - @Primary - @Qualifier][link-spring-boot-2]
3. [「Spring Boot #3」Spring Bean Life Cycle + @PostConstruct và @PreDestroy][link-spring-boot-3]
4. [「Spring Boot #4」@Component vs @Service vs @Repository][link-spring-boot-4]
5. [「Spring Boot #5」Component Scan là gì?][link-spring-boot-5]
6. [「Spring Boot #6」@Configuration và @Bean][link-spring-boot-6]
7. [「Spring Boot #7」Spring Boot Application Config và @Value][link-spring-boot-7]
8. [「Spring Boot #8」Tạo Web Helloworld với @Controller][link-spring-boot-8]
[link-spring-boot-1]: https://loda.me/spring-boot-1-huong-dan-component-va-autowired-loda1557412317602
[link-spring-boot-2]: https://loda.me/spring-boot-2-autowired-primary-qualifier-loda1557561089057
[link-spring-boot-3]: https://loda.me/spring-boot-3-spring-bean-life-cycle-post-construct-va-pre-destroy-loda1557583753982
[link-spring-boot-4]: https://loda.me/spring-boot-4-component-vs-service-vs-repository-loda1557627097246
[link-spring-boot-5]: https://loda.me/spring-boot-5-component-scan-la-gi-loda1557673850320
[link-spring-boot-6]: https://loda.me/spring-boot-6-configuration-va-bean-loda1557885506910
[link-spring-boot-7]: https://loda.me/spring-boot-7-spring-boot-application-config-va-value-loda1558171356103
[link-spring-boot-8]: https://loda.me/spring-boot-8-tao-web-helloworld-voi-controller-loda1558189401113
[link-github]: https://github.com/loda-kun/spring-boot-learning
