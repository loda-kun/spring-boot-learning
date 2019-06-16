package me.loda.spring.jpaauditing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 2019-06-06
 * Github: https://github.com/loda-kun
 */
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
