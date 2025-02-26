package com.example.spotifyplaylist.model.service;

import com.example.spotifyplaylist.model.entity.StyleName;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SongServiceModel {

    private String id;

    @NotBlank(message = "")
    @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters.")
    private String performer;

    @NotBlank(message = "")
    @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters.")
    private String title;

    @FutureOrPresent(message = "The date cannot be in the future!")
    private LocalDate releaseDate;

    @Positive(message = "Duration must be positive")
    private Integer duration;

    @NotNull(message = "You must select a style!")
    private StyleName style;
}
