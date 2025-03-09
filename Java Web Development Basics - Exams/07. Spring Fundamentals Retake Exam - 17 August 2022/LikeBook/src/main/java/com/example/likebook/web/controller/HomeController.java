package com.example.likebook.web.controller;

import com.example.likebook.model.view.HomeViewModel;
import com.example.likebook.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping(value = {"/index", "/"})
    public String index() {

        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home(){
    ModelAndView modelAndView = new ModelAndView("home");

        HomeViewModel homeViewModel = postService.getHomeViewModel();
        modelAndView.addObject("homeViewModel", homeViewModel);

        return modelAndView;
    }
}
