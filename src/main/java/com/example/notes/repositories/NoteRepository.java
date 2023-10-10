package com.example.notes.repositories;

import com.example.notes.models.Note;
import com.example.notes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<List<Note>> findNoteByUser(User user);

    Optional<List<Note>> findNoteByUserOrderByLastModifiedDesc(User user);

    Optional<Note> findNoteById(Long id);

}
