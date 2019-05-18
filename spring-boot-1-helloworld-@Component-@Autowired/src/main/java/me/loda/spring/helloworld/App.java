package me.loda.spring.helloworld;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/9/2019
 * Github: https://github.com/loda-kun
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        // ApplicationContext chính là container, chứa toàn bộ các Bean
        ApplicationContext context = SpringApplication.run(App.class, args);

        // Khi chạy xong, lúc này context sẽ chứa các Bean có đánh
        // dấu @Component.

        // Lấy Bean ra bằng cách
        Outfit outfit = context.getBean(Outfit.class);

        // In ra để xem thử nó là gì
        System.out.println("Output Instance: " + outfit);
        // xài hàm wear()
        outfit.wear();

        Girl girl = context.getBean(Girl.class);

        System.out.println("Girl Instance: " + girl);

        System.out.println("Girl Outfit: " + girl.outfit);

        girl.outfit.wear();

        // Kiểm tra 2 đối tượng
        System.out.println((girl.outfit == outfit));
    }
}
