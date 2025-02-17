package app.story.model;

import app.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 25)
    private String title;

    @Column(nullable = false, columnDefinition = "text", length = 1000)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Kind kind;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDate addedOn;

    @ManyToOne
    private User addedBy;

    @Column
    private boolean isVisible;
}