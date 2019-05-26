package me.loda.spring.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Todo {
    private String title;
    private String detail;
}
