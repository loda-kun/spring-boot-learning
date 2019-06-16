package me.loda.spring.applicationcontextevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 2019-06-06
 * Github: https://github.com/loda-kun
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @EventListener
    public void contextRefreshedEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Khi Application chạy lần đầu hoặc refreshing nó sẽ bắn sự kiện ContextRefreshedEvent");
    }

    @EventListener
    public void contextStartedEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("Khi ApplicationContext được khởi tạo xong bởi .start() nó sẽ bắn sự kiện ContextRefreshedEvent");
    }

    @EventListener
    public void contextStoppedEvent(ContextStoppedEvent contextStoppedEvent) {
        System.out.println("Khi ApplicationContext bị stop bởi lệnh .stop()");
    }

    @EventListener
    public void contextClosedEvent(ContextClosedEvent contextClosedEvent) {
        System.out.println("Khi ApplicationContext bị close hoàn toàn bởi lệnh .closed()");
    }

}
