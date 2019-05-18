package me.loda.spring.helloprimaryqualifier;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/11/2019
 * Github: https://github.com/loda-kun
 */
/*
 Đánh dấu class bằng @Component
 Class này sẽ được Spring Boot hiểu là một Bean (hoặc dependency)
 Và sẽ được Spring Boot quản lý
  */
@Component("bikini")
public class Bikini implements Outfit {
    @Override
    public void wear() {
        System.out.println("Mặc bikini");
    }
}
