package me.loda.spring.postmappingmodelattibute;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {
    List<Todo> todoList = new CopyOnWriteArrayList<>();

    @GetMapping("/listTodo")
    public String index(Model model) {
        model.addAttribute("todoList", todoList);
        return "listTodo";
    }

    @GetMapping("/addTodo")
    public String addTodo(Model model) {
        model.addAttribute("todo", new Todo());
        return "addTodo";
    }

    @PostMapping("/addTodo")
    public String addTodo(@ModelAttribute Todo todo) {
        todoList.add(todo);
        return "success";
    }
}
