package com.example.notes.services;

import com.example.notes.models.Note;
import com.example.notes.models.User;
import com.example.notes.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {


    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNoteByUser(User user){
        Optional<List<Note>> notesOptional = noteRepository.findNoteByUser(user);
        if (notesOptional.isEmpty()) {
            throw new RuntimeException("Записи пользователя не найдены");
        }
        return notesOptional.get();
    }


    public Note getNoteById(Long id){
        Optional<Note> noteOptional = noteRepository.findNoteById(id);
        if (noteOptional.isEmpty()){
            throw new RuntimeException("Запись не найдена");
        }
        return noteOptional.get();
    }


    public Note createNote(String text, User user, String title){
        try{
            Note note = new Note();
            note.setText(text);
            note.setUser(user);
            note.setTitle(title);
            noteRepository.save(note);
            return note;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Note editNote(String text, Note note){
        try {
            note.setText(text);
            noteRepository.save(note);
            return note;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteNote(Note note){
        try {
            noteRepository.delete(note);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



}
