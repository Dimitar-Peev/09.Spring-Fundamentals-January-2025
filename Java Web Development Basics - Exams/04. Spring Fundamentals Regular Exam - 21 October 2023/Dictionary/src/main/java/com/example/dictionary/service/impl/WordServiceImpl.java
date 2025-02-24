package com.example.dictionary.service.impl;

import com.example.dictionary.model.entity.Language;
import com.example.dictionary.model.entity.LanguageName;
import com.example.dictionary.model.entity.Word;
import com.example.dictionary.model.service.WordServiceModel;
import com.example.dictionary.model.view.HomeViewModel;
import com.example.dictionary.model.view.WordViewModel;
import com.example.dictionary.repository.WordRepository;
import com.example.dictionary.service.LanguageService;
import com.example.dictionary.service.UserService;
import com.example.dictionary.service.WordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final LanguageService languageService;
    private final UserService userService;

    @Override
    public boolean add(WordServiceModel wordServiceModel, String username) {
        Language language = this.languageService.findByName(wordServiceModel.getLanguage());

        if (language != null) {
            Word word = this.modelMapper.map(wordServiceModel, Word.class);
//            Word word = new Word();
//            word.setTerm(wordServiceModel.getTerm());
//            word.setTranslation(wordServiceModel.getTranslation());
//            word.setExample(wordServiceModel.getExample());
//            word.setInputDate(wordServiceModel.getInputDate());
            word.setLanguage(language);
            word.setAddedBy(userService.findUserByUsername(username));
            this.wordRepository.save(word);
            log.info("Successfully added word.");
            return true;
        } else {
            log.warn("Failed to add word.");
            return false;
        }

    }

    @Override
    public List<WordServiceModel> getWordsByLanguage(LanguageName language) {

        return wordRepository.findWordByLanguageName(language)
                .stream()
                .map(word -> {
                    WordServiceModel wordServiceModel = this.modelMapper.map(word, WordServiceModel.class);
                    wordServiceModel.setInputDate(word.getInputDate());
                    wordServiceModel.setLanguage(word.getLanguage().getName());
                    wordServiceModel.setAddedBy(word.getAddedBy());
                    return wordServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public HomeViewModel getHomeViewModel() {
        HomeViewModel homeViewModel = new HomeViewModel();

        List<WordViewModel> spanishWords = getWordsByLanguage(LanguageName.SPANISH)
                .stream()
                .map(wordServiceModel -> modelMapper.map(wordServiceModel, WordViewModel.class))
                .toList();

        List<WordViewModel> germanWords = getWordsByLanguage(LanguageName.GERMAN)
                .stream()
                .map(wordServiceModel -> modelMapper.map(wordServiceModel, WordViewModel.class))
                .toList();

        List<WordViewModel> frenchWords = getWordsByLanguage(LanguageName.FRENCH)
                .stream()
                .map(wordServiceModel -> modelMapper.map(wordServiceModel, WordViewModel.class))
                .toList();

        List<WordViewModel> italianWords = getWordsByLanguage(LanguageName.ITALIAN)
                .stream()
                .map(wordServiceModel -> modelMapper.map(wordServiceModel, WordViewModel.class))
                .toList();

        homeViewModel.setSpanishWords(spanishWords);
        homeViewModel.setFrenchWords(frenchWords);
        homeViewModel.setItalianWords(italianWords);
        homeViewModel.setGermanWords(germanWords);
        homeViewModel.setTotalWordCount(getTotalWordCount());

        return homeViewModel;
    }

    @Override
    public long getTotalWordCount() {
        return this.wordRepository.count();
    }

    @Override
    public void delete(String id) {
        this.wordRepository.deleteById(id);
        log.info("Successfully deleted a word.");
    }

    @Override
    public void deleteAll() {
        this.wordRepository.deleteAll();
        log.info("Successfully deleted all words.");
    }

}
