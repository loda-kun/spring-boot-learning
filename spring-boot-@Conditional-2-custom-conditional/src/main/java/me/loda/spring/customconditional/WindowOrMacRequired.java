package me.loda.spring.customconditional;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.context.annotation.Conditional;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 2019-06-04
 * Github: https://github.com/loda-kun
 */

/**
 * Class kế thừa AnyNestedCondition sẽ chấp nhận mọi
 * điều kiện @Conditional bên trong nó
 */
public class WindowOrMacRequired extends AnyNestedCondition{

    public WindowOrMacRequired(){
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    /*
    Bạn phải định nghĩa các Điều kiện bên trong class
    kế thừa AnyNestedCondition
     */
    @Conditional(WindowRequired.class)
    public class RunOnWindow{}

    /*
    Lúc này, cả 2 điều kiện Window và Mac sẽ được kết hợp vs
    nhau khi kiểm tra, nếu thoả mãn 1 trong 2 là đc
     */
    @Conditional(MacRequired.class)
    public class RunOnMac{}
}
