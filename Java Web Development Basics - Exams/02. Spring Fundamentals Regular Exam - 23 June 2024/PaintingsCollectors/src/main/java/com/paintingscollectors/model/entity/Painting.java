package com.paintingscollectors.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "paintings")
public class Painting {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 30)
    private String author;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Style style;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User owner;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private boolean isFavorite;

    @Column(nullable = false)
    private int votes;
}
