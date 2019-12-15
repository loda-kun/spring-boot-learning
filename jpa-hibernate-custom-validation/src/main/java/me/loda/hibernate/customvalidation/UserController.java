package me.loda.hibernate.customvalidation;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    /*
        Đánh dấu object với @Valid để validator tự động kiểm tra object đó có hợp lệ hay không
     */
    @PostMapping
    public Object createUser(@Valid @RequestBody User user) {
        return user;
    }

}
