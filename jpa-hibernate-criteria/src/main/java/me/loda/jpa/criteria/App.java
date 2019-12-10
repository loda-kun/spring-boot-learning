package me.loda.jpa.criteria;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;
import me.loda.jpa.criteria.User.UserType;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 12/10/2019
 * Github: https://github.com/loda-kun
 */
@SpringBootApplication
@RequiredArgsConstructor
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    private final UserRepository userRepository;
    private final CustomUserRepository customUserRepository;

    @Bean
    CommandLineRunner run() {
        return args -> {
            // Get User by ID
            System.out.println(customUserRepository.getUserById(10L));
            System.out.println("=======");

            // Get User by Name Like and Type
            System.out.println(customUserRepository.getUserByComplexConditions("name-%", UserType.NORMAL));
            System.out.println("=======");
        };
    }

}
