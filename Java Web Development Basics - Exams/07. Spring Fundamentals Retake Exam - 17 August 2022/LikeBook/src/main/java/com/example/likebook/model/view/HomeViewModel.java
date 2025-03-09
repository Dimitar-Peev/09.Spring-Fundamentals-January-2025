package com.example.likebook.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeViewModel {

    List<PostViewModel> userPosts = new ArrayList<>();
    List<PostViewModel> otherPosts = new ArrayList<>();
}
