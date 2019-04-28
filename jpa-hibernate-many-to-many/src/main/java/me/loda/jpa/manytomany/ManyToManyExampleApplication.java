package me.loda.jpa.manytomany;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/


import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.collect.Lists;

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
public class ManyToManyExampleApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ManyToManyExampleApplication.class, args);
    }

    // Sử dụng @RequiredArgsConstructor và final để thay cho @Autowired
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Tạo ra đối tượng Address
        Address hanoi = Address.builder()
                                 .city("hanoi")
                                 .build();
        Address hatay = Address.builder()
                               .city("hatay")
                               .build();

        // Tạo ra đối tượng person
        Person person1 = Person.builder()
                              .name("loda1")
                              .build();
        Person person2 = Person.builder()
                              .name("loda2")
                              .build();

        // set Persons vào address
        hanoi.setPersons(Lists.newArrayList(person1, person2));
        hatay.setPersons(Lists.newArrayList(person1));

        // Lưu vào db
        // Chúng ta chỉ cần lưu address, vì cascade = CascadeType.ALL nên nó sẽ lưu luôn Person.
        addressRepository.saveAndFlush(hanoi);
        addressRepository.saveAndFlush(hatay);


        // Vào: http://localhost:8080/h2-console/ để xem dữ liệu đã insert

        Address queryResult = addressRepository.findById(1L).get();
        System.out.println(queryResult.getCity());
        System.out.println(queryResult.getPersons());

    }

}
