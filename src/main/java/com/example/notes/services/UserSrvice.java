package com.example.notes.services;


import com.example.notes.models.Note;
import com.example.notes.models.User;
import com.example.notes.repositories.NoteRepository;
import com.example.notes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSrvice {

    UserRepository userRepository;

    NoteService noteService;

    @Autowired
    public UserSrvice(UserRepository userRepository, NoteService noteService) {
        this.userRepository = userRepository;
        this.noteService = noteService;
    }

    public User getUserByEmail(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()){
            throw new RuntimeException("Пользователь не найден");
        }
        return userOptional.get();
    }

}
