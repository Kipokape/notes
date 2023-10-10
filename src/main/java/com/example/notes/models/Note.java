package com.example.notes.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

}
