package me.loda.jpa.manytomany;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 4/5/2019
 * Github: https://github.com/loda-kun
 */
// Kế thừa JpaRepository để giao tiếp với database
public interface AddressRepository extends JpaRepository<Address,Long> {
}
