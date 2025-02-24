package com.example.dictionary.model.view;

import lombok.*;

import java.util.List;

@Data
public class HomeViewModel {

    List<WordViewModel> spanishWords;
    List<WordViewModel> germanWords;
    List<WordViewModel> frenchWords;
    List<WordViewModel> italianWords;
    long totalWordCount;
}
