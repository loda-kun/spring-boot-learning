package me.loda.jpa.manytomany;
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

import lombok.RequiredArgsConstructor;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 4/5/2019
 * Github: https://github.com/loda-kun
 */
@SpringBootApplication
@RequiredArgsConstructor
public class OneToOneExampleApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(OneToOneExampleApplication.class, args);
    }

    // Sử dụng @RequiredArgsConstructor và final để thay cho @Autowired
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
        // Tạo ra đối tượng person
        Person person = Person.builder()
                              .name("loda")
                              .build();
        // Lưu vào db
        personRepository.save(person);

        // Tạo ra đối tượng Address có tham chiếu tới person
        Address address = Address.builder()
                .city("Hanoi")
                .person(person)
                .build();

        // Lưu vào db
        addressRepository.save(address);

        // Vào: http://localhost:8080/h2-console/ để xem dữ liệu đã insert
    }
}
