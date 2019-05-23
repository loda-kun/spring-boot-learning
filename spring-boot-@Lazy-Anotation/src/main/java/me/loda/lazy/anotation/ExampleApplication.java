package me.loda.lazy.anotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ExampleApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExampleApplication.class);
        System.out.println("ApplicationContext started!");
        FirstBean firstBean = context.getBean(FirstBean.class);
        SecondBean secondBean = context.getBean(SecondBean.class);
        context.close();
    }
}
