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
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 150)
    private String content;

    @ManyToOne(optional = false)
    private User user;

    @ManyToMany
    private Set<User> userLikes = new HashSet<>();

    @ManyToOne(optional = false)
    private Mood mood;
}
