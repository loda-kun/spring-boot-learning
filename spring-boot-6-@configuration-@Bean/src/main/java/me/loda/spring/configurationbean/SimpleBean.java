package me.loda.spring.configurationbean;
/**
 * For Vietnamese readers:
 * Các bạn thân mến, mình rất vui nếu project này giúp
 * ích được cho các bạn trong việc học tập và công việc. Nếu
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để
 * lại dường dẫn tới github hoặc tên tác giá.
 * Xin cảm ơn!
 * <p>
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/16/2019
 * Github: https://github.com/loda-kun
 * <p>
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 * @since 5/16/2019
 * Github: https://github.com/loda-kun
 */

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/16/2019
 * Github: https://github.com/loda-kun
 */

/**
 * Một class cơ bản, không sử dụng `@Component`
 */
public class SimpleBean {
    private String username;

    public SimpleBean(String username) {
        setUsername(username);
    }

    @Override
    public String toString() {
        return "This is a simple bean, name: " + username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
