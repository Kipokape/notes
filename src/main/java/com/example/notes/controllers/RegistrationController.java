package com.example.notes.controllers;


import com.example.notes.dto.RegisterDto;
import com.example.notes.repositories.UserRepository;
import com.example.notes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

@Controller
public class RegistrationController {

    private UserRepository userRepository;

    private UserService userService;

    @Autowired
    public RegistrationController(UserRepository userRepository,
                                  UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(RegisterDto registerDto, RedirectAttributes redirectAttributes) {
        if (userService.isExistsByEmailUser(registerDto.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Email занят!");
            return "redirect:/registration";
        }
        userService.registrationUser(registerDto.getUsername(), registerDto.getPassword());
        return "redirect:/login";
    }

}
