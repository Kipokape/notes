package com.example.notes.dto;

import com.example.notes.models.User;
import lombok.Data;

@Data
public class NoteDto {

    private String title;
    private String text;
    private User user;
}
