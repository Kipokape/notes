package com.example.notes.services;

import com.example.notes.models.Note;
import com.example.notes.models.User;
import com.example.notes.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        Optional<List<Note>> notesOptional = noteRepository.findNoteByUserOrderByLastModifiedDesc(user);
        if (notesOptional.isEmpty()) {
            throw new RuntimeException("Записи пользователя не найдены");
        }
        return notesOptional.get();
    }

    public boolean isUserNote(Note note, User user){
        return note.getUser() == user;
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
            note.setLastModified(LocalDateTime.now());
            noteRepository.save(note);
            return note;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Note editNote(String text, String title, Note note){
        try {
            note.setText(text);
            note.setTitle(title);
            note.setLastModified(LocalDateTime.now());
            noteRepository.save(note);
            return note;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteNote(Note note, User user){
        try {
            if (isUserNote(note, user)){
                noteRepository.delete(note);
                return true;
            }
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



}
