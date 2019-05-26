package me.loda.spring.springprofiles;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/26/2019
 * Github: https://github.com/loda-kun
 */
@SpringBootApplication
public class App {
    @Autowired
    private ConfigurableEnvironment env;

    public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
        /**
         * Cách 3
         */
        SpringApplication application = new SpringApplication(App.class);
        ConfigurableEnvironment environment = new StandardEnvironment();
//        Thay đổi môi trường bằng cách comment và xem kết quả
        environment.setActiveProfiles("local");
//        environment.setActiveProfiles("aws");
        application.setEnvironment(environment);
        ApplicationContext context = application.run(args);

        LocalDatasource localDatasource = context.getBean(LocalDatasource.class);
        System.out.println(localDatasource);

        /**
         * Cách 4: -Dspring.profiles.active=aws
         */

        /**
         * CÁch 5: export SPRING_PROFILES_ACTIVE=aws
         */

        /**
         * Cách 6: Sử dụng Inteliji plugin.
         */
    }

    /**
     * Cách 2
     * @return
     */
    @Bean
    CommandLineRunner init(){
        return args -> {
            env.setActiveProfiles("aws");
        };
    }
}
