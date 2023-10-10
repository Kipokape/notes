package com.example.notes.controllers;


import com.example.notes.models.Note;
import com.example.notes.models.User;
import com.example.notes.services.NoteService;
import com.example.notes.services.UserSrvice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    UserSrvice userSrvice;

    NoteService noteService;

    @Autowired
    public UserController(UserSrvice userSrvice, NoteService noteService) {
        this.userSrvice = userSrvice;
        this.noteService = noteService;
    }


    @GetMapping("/welcome")
    public String welcome(Model model,
                          Authentication authentication) {

        User user = userSrvice.getUserByEmail(authentication.getName());
        List<Note> notes = noteService.getAllNoteByUser(user);
        model.addAttribute("notes", notes);
        return "welcome";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }







}
