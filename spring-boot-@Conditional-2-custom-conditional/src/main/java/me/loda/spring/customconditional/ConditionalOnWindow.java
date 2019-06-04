package me.loda.spring.customconditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 2019-06-04
 * Github: https://github.com/loda-kun
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
// Đánh dấu annotation này bởi @Conditional(WindowRequired.class)
@Conditional(WindowRequired.class)
public @interface ConditionalOnWindow {
    /*
    Trong trường hợp bạn muốn viết ngắn gọn,
    hay tạo ra 1 Annotation mới và gắn @Conditional(WindowRequired.class)
    trên nó

    Như vậy khi cần sử dụng chỉ cần gọi @ConditionalOnWindow là được
     */
}
