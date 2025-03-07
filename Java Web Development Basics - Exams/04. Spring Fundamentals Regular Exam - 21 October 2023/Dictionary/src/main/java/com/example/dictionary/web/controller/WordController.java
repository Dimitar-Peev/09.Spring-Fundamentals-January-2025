package com.example.dictionary.web.controller;

import com.example.dictionary.constant.Constants;
import com.example.dictionary.model.binding.WordAddBindingModel;
import com.example.dictionary.model.service.WordServiceModel;
import com.example.dictionary.service.WordService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/words")
@AllArgsConstructor
public class WordController {

    private final WordService wordService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    @GetMapping("/add")
    public String add(Model model) {
        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("wordAddBindingModel")) {
            model.addAttribute("wordAddBindingModel", new WordAddBindingModel());
        }

        return "word-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid WordAddBindingModel wordAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("wordAddBindingModel", wordAddBindingModel);
            redirectAttributes.addFlashAttribute(Constants.BINDING_MODEL + "wordAddBindingModel", bindingResult);
            return "redirect:add";
        }

        WordServiceModel wordServiceModel = this.modelMapper.map(wordAddBindingModel, WordServiceModel.class);

        String username = httpSession.getAttribute("username").toString();
        boolean success = this.wordService.add(wordServiceModel,  username);

        if (!success) {
            redirectAttributes.addFlashAttribute("wordAddBindingModel", wordAddBindingModel);
            return "redirect:/add";
        }

        return "redirect:/home";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteWord(@PathVariable String id) {
        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        wordService.delete(id);

        return "redirect:/home";
    }

    @GetMapping("/remove-all")
    public String removeAll() {
        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        wordService.deleteAll();

        return "redirect:/home";
    }
}
