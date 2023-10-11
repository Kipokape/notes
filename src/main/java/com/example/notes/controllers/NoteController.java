package com.example.notes.controllers;

import com.example.notes.dto.NoteDto;
import com.example.notes.models.Note;
import com.example.notes.models.User;
import com.example.notes.services.NoteService;
import com.example.notes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {

    private NoteService noteService;

    private UserService userService;

    @Autowired
    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/note")
    public String getNotePage(@RequestParam(value = "note_id", required = false) Note note,
                              Authentication authentication,
                              Model model){

        User user = userService.getUserByEmail(authentication.getName());
        if(note!=null){
            if (noteService.isUserNote(note, user)){
                model.addAttribute("note", note);
            }else note = new Note();
        }
        else {
            note = new Note();
        }
        model.addAttribute("note", note);
        return "notePage";
    }

    @PostMapping("/note/create")
    public String createNote(NoteDto noteDto,
                             @RequestParam(value = "note_id", required = false) Note note,
                             Model model,
                             Authentication authentication){

        User user = userService.getUserByEmail(authentication.getName());
        if (note!=null){
            if (noteService.isUserNote(note, user)){
                noteService.editNote(noteDto.getText(), noteDto.getTitle(), note);
            }
        }
        else {
            noteService.createNote(noteDto.getText(), user, noteDto.getTitle());
        }
        return "redirect:/home";
    }

    @PostMapping("/note/delete")
    public String deleteNote(@RequestParam("note_id") Note note,
                             Model model,
                             Authentication authentication){

        User user = userService.getUserByEmail(authentication.getName());
        if(noteService.isUserNote(note, user)){
            noteService.deleteNote(note, user);
        }

        return "redirect:/home";
    }

}
