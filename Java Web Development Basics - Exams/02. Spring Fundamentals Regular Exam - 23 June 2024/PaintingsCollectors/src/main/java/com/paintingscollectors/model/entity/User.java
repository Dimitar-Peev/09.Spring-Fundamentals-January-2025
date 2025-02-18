package com.paintingscollectors.model.entity;

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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Painting> paintings = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Painting> favoritePaintings = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Painting> ratedPaintings = new HashSet<>();
}
