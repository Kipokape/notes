package com.example.notes.controllers;


import com.example.notes.dto.RegisterDto;
import com.example.notes.models.Role;
import com.example.notes.models.User;
import com.example.notes.repositories.RoleRepository;
import com.example.notes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

@Controller
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder,
                                  RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(RegisterDto registerDto, RedirectAttributes redirectAttributes) {
        if (userRepository.existsByEmail(registerDto.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Email занят!");
            return "redirect:/registration";
        }

        User user = new User();
        user.setEmail(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return "redirect:/login";
    }

}
