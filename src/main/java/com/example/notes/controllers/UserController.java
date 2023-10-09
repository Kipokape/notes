package com.example.notes.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
