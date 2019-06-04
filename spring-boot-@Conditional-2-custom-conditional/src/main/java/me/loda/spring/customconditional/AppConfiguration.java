package me.loda.spring.customconditional;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 6/3/2019
 * Github: https://github.com/loda-kun
 */
@Configuration
public class AppConfiguration {
    public static class SomeBean{
    }

    /*
    SomeBean chỉ được tạo ra khi
    thỏa mãn điều kiện
     */
//    @Conditional(MacRequired.class)
//    @Conditional(WindowRequired.class)
//    @Conditional(WindowOrMacRequired.class)
    @ConditionalOnWindow
    @Bean
    SomeBean someBean(){
        return new SomeBean();
    }
}
