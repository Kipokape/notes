package com.example.notes.controllers;

import com.example.notes.models.Note;
import com.example.notes.models.User;
import com.example.notes.services.NoteService;
import com.example.notes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    private NoteService noteService;

    @Autowired
    public UserController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping("/home")
    public String welcome(Model model,
                          Authentication authentication) {

        User user = userService.getUserByEmail(authentication.getName());
        List<Note> notes = noteService.getAllNoteByUser(user);
        model.addAttribute("notes", notes);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


}
