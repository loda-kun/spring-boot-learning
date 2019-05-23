package me.loda.lazy.anotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class ServiceBean {

    @Lazy
    @Autowired
    private FirstBean firstBean;

    public FirstBean getFirstBean() {
        return firstBean;
    }
}
