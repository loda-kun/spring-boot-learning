# Source
Vào link để xem chi tiết có hình ảnh minh họa:

[Loda.me - [Spring Boot] Các sự kiện của ApplicationContext][loda-link]

[loda-link]: https://loda.me/spring-boot-cac-su-kien-cua-application-context-loda1559810617419

# Content without images

### Giới thiệu

Chúng ta có thể tạo ra các `Event` và `Listener` trong Spring Boot để xử lý công việc một cách gọn gàng và thông minh.

Nếu bạn chưa biết hãy xem:

1. [Xử lý sự kiện với @EventListener + @Async][link-spring-event]

Bản thân Spring Boot cũng tự định nghĩa và có cho nó một số sự kiện riêng ở mức Application, và chúng ta có thể lắng nghe nó để quản lý vòng đời của ứng dụng của mình.

### ContextStartedEvent

`ContextStartedEvent` sụ kiện xảy ra khi `ApplicationContext` được khởi tạo.

Chúng ta có thể lắng nghe sự kiện này bằng `@EventListener` đã giới thiệu [tại đây][link-spring-event]

```java
    @EventListener
    public void contextStartedEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("Khi ApplicationContext được khởi tạo xong bởi .start() nó sẽ bắn sự kiện ContextRefreshedEvent");
    }
```

### ContextRefreshedEvent

`ContextRefreshedEvent` sự kiện xảy ra mỗi khi `ApplicationContext1` khởi tạo hoặc bị refreshing.

Trong ứng dụng, `ApplicationContext` có thể bị refresh nhiều lần.

```java

    @EventListener
    public void contextRefreshedEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Khi Application chạy lần đầu hoặc refreshing nó sẽ bắn sự kiện ContextRefreshedEvent");
    }

```

### ContextStoppedEvent

> Nguồn bài viết [https://loda.me](https://loda.me) - rất nhiều thứ hay ho :'(

Xảy ra khi `ApplicationContext` bị stop lại bởi lệnh `.stop()`

```java
    @EventListener
    public void contextStoppedEvent(ContextStoppedEvent contextStoppedEvent) {
        System.out.println("Khi ApplicationContext bị stop bởi lệnh .stop()");
    }

```

### ContextClosedEvent

Xảy ra khi `ApplicationContext` bị đóng hoàn toàn bởi lệnh `.closed`

```java

    @EventListener
    public void contextClosedEvent(ContextClosedEvent contextClosedEvent) {
        System.out.println("Khi ApplicationContext bị close hoàn toàn bởi lệnh .closed()");
    }
```

### Kết

Bạn có thể clone code về chạy thử và tự test. Việc xác định vòng đời của Context sẽ giúp chúng ta thực hiện các tác vụ ngầm khi cần thiết.

Và như mọi khi, [toàn bộ code đều được up lên Github][link-github].
<a class="btn btn-icon btn-github mr-1" target="_blank" href="https://github.com/loda-kun/spring-boot-learning">
<i class="fab fa-github"></i>
</a>

[link-github]: https://github.com/loda-kun/spring-boot-learning
[link-spring-event]: https://loda.me/spring-boot-xu-ly-su-kien-voi-event-listener-async-loda1559286387703
