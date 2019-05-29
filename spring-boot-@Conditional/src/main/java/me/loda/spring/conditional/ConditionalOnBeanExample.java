package me.loda.spring.conditional;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
public class ConditionalOnBeanExample {
    /*
    Đây là một Bean, bạn hãy chạy ứng dụng
    khi comment và chạy lại lần nữa nhưng bỏ dấu comment phía
    dưới để xem kết quả.
     */
    // @Bean
    RandomBean randomBean() {
        return new RandomBean();
    }

    /*
    ABeanWithCondition chỉ được tạo ra, khi RandomBean tồn tại trong Context.
     */
    @Bean
    @ConditionalOnBean(RandomBean.class)
    ABeanWithCondition aBeanWithACondition() {
        return new ABeanWithCondition();
    }
}
