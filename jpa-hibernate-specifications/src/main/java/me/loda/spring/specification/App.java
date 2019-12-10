package me.loda.spring.specification;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import static me.loda.spring.specification.User.UserType.NORMAL;
import static me.loda.spring.specification.UserSpecification.*;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;
import me.loda.spring.specification.User.UserType;

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

    @Bean
    CommandLineRunner run() {
        return args -> {
            // Lấy ra user nằm trong tập ID đã cho và có type là NORMAL
            // hoặc lấy ra user có ID = 10
            Specification conditions = Specification.where(hasIdIn(Arrays.asList(1L, 2L, 3L, 4L, 5L)))
                                                    .and(hasType(NORMAL))
                                                    .or(hasId(10L));
            // Truyền Specification vào hàm findAll()
            userRepository.findAll(conditions).forEach(System.out::println);
        };
    }

}
