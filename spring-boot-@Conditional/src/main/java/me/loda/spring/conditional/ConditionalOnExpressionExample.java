package me.loda.spring.conditional;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/29/2019
 * Github: https://github.com/loda-kun
 */
/*
Configuration ConditionalOnExpressionExample chỉ được tạo
khi cả 2 điều kiện thỏa mãn
 */
@Configuration
@ConditionalOnExpression(
        "${loda.expression1.enabled:true} and ${loda.expression2.enabled:true}"
)
public class ConditionalOnExpressionExample {
}
