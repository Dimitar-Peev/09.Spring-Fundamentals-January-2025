package com.example.likebook.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostViewModel {

    private String id;
    private String content;
    private String mood;
    private String userUsername;
    private int likes;
}
