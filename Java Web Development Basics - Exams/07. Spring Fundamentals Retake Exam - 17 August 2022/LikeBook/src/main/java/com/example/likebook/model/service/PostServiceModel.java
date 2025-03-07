package com.example.likebook.model.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostServiceModel {

    private String id;

    @NotBlank
    @Size(min = 2, max = 50, message = "Content length must be between 2 and 50 characters!")
    private String content;

    @NotNull(message = "You must select a mood!")
    private String mood;

    private String userId;
}
