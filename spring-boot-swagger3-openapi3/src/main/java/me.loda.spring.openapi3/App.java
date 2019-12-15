package me.loda.spring.openapi3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        // http://localhost:8080/v3/api-docs/ to show api document as a json
        // http://localhost:8080/swagger-ui/index.html to show api in the web-view
        SpringApplication.run(App.class, args);
    }
}
