package me.loda.spring.thymeleaf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/profile")
    public String profile(Model model){
        List<Info> profile = new ArrayList<>();
        profile.add(new Info("name", "Nguyễn Hoàng Nam"));
        profile.add(new Info("gmail", "namhn1495@gmail.com"));
        profile.add(new Info("facebook", "https://www.facebook.com/nam.tehee"));
        profile.add(new Info("website", "https://loda.me"));

        model.addAttribute("lodaProfile", profile);
        return "profile";
    }
}
