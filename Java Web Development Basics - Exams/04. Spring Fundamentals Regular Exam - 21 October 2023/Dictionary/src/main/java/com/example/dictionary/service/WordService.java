package com.example.dictionary.service;

import com.example.dictionary.model.entity.LanguageName;
import com.example.dictionary.model.service.WordServiceModel;
import com.example.dictionary.model.view.HomeViewModel;

import java.util.List;

public interface WordService {

    boolean add(WordServiceModel wordServiceModel, String username);

    List<WordServiceModel> getWordsByLanguage(LanguageName language);

    HomeViewModel getHomeViewModel();

    long getTotalWordCount();

    void delete(String id);

    void deleteAll();
}
