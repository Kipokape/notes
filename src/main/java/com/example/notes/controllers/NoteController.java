package com.example.notes.controllers;

import com.example.notes.models.Note;
import com.example.notes.models.User;
import com.example.notes.services.NoteService;
import com.example.notes.services.UserSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {

    NoteService noteService;

    UserSrvice userSrvice;

    @Autowired
    public NoteController(NoteService noteService, UserSrvice userSrvice) {
        this.noteService = noteService;
        this.userSrvice = userSrvice;
    }

    @GetMapping("/note")
    public String getNotePage(@RequestParam(value = "note_id", required = false) Note note,
                              Model model){
        if(note==null){
            note = new Note();
        }
        model.addAttribute("note", note);
        return "notePage";
    }

    @PostMapping("/note/create")
    public String createNote(@RequestParam String text,
                             @RequestParam String title,
                             Model model,
                             Authentication authentication){

        User user = userSrvice.getUserByEmail(authentication.getName());
        noteService.createNote(text,user, title);

        return "redirect:/welcome";
    }

    @PostMapping("/note/delete")
    public String deleteNote(@RequestParam("note_id") Note note,
                             Model model,
                             Authentication authentication){

        User user = userSrvice.getUserByEmail(authentication.getName());
        noteService.deleteNote(note);

        return "redirect:/welcome";
    }

}
