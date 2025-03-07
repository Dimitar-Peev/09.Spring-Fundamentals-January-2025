package com.example.likebook.model.binding;

import com.example.likebook.model.entity.MoodName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostAddBindingModel {

    @NotBlank(message = "")
    @Size(min = 2, max = 150, message = "Content length must be between 2 and 150 characters!")
    private String content;

    @NotNull(message = "You must select a mood!")
    private MoodName mood;
}
