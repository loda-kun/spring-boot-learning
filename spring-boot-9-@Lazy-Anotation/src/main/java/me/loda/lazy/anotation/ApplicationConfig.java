package me.loda.lazy.anotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Configuration
public class ApplicationConfig {


    @Bean
    public SecondBean secondBean() {
        return new SecondBean();
    }
}
