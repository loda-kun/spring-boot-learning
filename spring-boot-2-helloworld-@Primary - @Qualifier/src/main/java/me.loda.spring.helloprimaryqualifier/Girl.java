package me.loda.spring.helloprimaryqualifier;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 5/11/2019
 * Github: https://github.com/loda-kun
 */
@Component
public class Girl {

    // Đánh dấu để Spring inject một đối tượng Outfit vào đây
    Outfit outfit;

    // Spring sẽ inject outfit thông qua Constructor đầu tiên
    // Ngoài ra, nó sẽ tìm Bean có @Qualifier("naked") trong context để ịnject
    public Girl(@Qualifier("naked") Outfit outfit) {
        this.outfit = outfit;
    }


    // GET
    // SET

    // Sử dụng trên method cũng được
    // @Autowired
    // public void setOutfit(Outfit outfit){
    //     this.outfit = outfit;
    // }
}
