package me.loda.spring.conditional;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/29/2019
 * Github: https://github.com/loda-kun
 */
@Configuration
public class ConditionalOnPropertyExample {

    /*
    @ConditionalOnProperty giúp gắn điều kiện cho @Bean dựa theo
    property trong config
     */
    @Bean
    @ConditionalOnProperty(
            value="loda.bean2.enabled",
            havingValue = "true", // Nếu giá trị loda.bean2.enabled  = true thì Bean mới được khởi tạo
            matchIfMissing = false) // matchIFMissing là giá trị mặc định nếu không tìm thấy property loda.bean2.enabled
    ABeanWithCondition2 aBeanWithCondition2(){
        return new ABeanWithCondition2();
    }
}
