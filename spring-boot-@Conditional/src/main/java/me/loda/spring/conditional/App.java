package me.loda.spring.conditional;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.ApplicationContext;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/29/2019
 * Github: https://github.com/loda-kun
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        try {
            ABeanWithCondition aBeanWithCondition = context.getBean(ABeanWithCondition.class);
            System.out.println("ABeanWithCondition tồn tại!");

        } catch (Exception e) {
            System.out.println("Bean ABeanWithCondition.class chỉ tồn tại khi RandomBean.class tồn tại");
        }

        try {
            ABeanWithCondition2 aBeanWithCondition2 = context.getBean(ABeanWithCondition2.class);
            System.out.println("ABeanWithCondition2 tồn tại!");

        } catch (Exception e) {
            System.out.println("Bean ABeanWithCondition2.class chỉ tồn tại khi loda.bean2.enabled=true tồn tại");
        }

        try {
            ConditionalOnResourceExample conditionalOnResourceExample = context.getBean(ConditionalOnResourceExample.class);
            System.out.println("ConditionalOnResourceExample tồn tại!");

        } catch (Exception e) {
            System.out.println("Bean ConditionalOnResourceExample.class chỉ tồn tại khi application.properties tồn tại");
        }
    }
}
