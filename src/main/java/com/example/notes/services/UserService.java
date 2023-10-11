package com.example.notes.services;


import com.example.notes.models.Role;
import com.example.notes.models.User;
import com.example.notes.repositories.RoleRepository;
import com.example.notes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private NoteService noteService;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, NoteService noteService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.noteService = noteService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()){
            throw new RuntimeException("Пользователь не найден");
        }
        return userOptional.get();
    }

    public boolean isExistsByEmailUser(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean registrationUser(String username, String password){
        try {
            User user = new User();
            user.setEmail(username);
            user.setPassword(passwordEncoder.encode(password));
            Role roles = roleRepository.findByName("USER").get();
            user.setRoles(Collections.singletonList(roles));
            userRepository.save(user);
            noteService.createNote("Пример текста", user, "Заметка 1");
            return  true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
