package com.example.likebook.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "moods")
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private MoodName name;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "mood", fetch = FetchType.EAGER)
    private Set<Post> posts = new HashSet<>();
}
