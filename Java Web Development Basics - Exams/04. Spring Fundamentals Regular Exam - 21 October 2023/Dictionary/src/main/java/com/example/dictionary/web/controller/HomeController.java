package com.example.dictionary.web.controller;

import com.example.dictionary.model.view.HomeViewModel;
import com.example.dictionary.service.WordService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class HomeController {

    private final WordService wordService;

    @GetMapping(value = {"/index", "/"})
    public ModelAndView index(HttpSession httpSession) {
        if (httpSession.getAttribute("loggedIn") != null) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home(HttpSession httpSession){
        if (httpSession.getAttribute("loggedIn") == null) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView("home");

        HomeViewModel homeViewModel = wordService.getHomeViewModel();
        modelAndView.addObject("homeViewModel", homeViewModel);

        String username = httpSession.getAttribute("username").toString();
        modelAndView.addObject("user", username);

        return modelAndView;
    }



}
