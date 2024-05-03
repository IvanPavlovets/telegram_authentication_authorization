package ru.job4j.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PersonController {

    @GetMapping({"/", "index"})
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
