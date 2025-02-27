package com.example.spotifyplaylist.web.controller;

import com.example.spotifyplaylist.model.view.HomeViewModel;
import com.example.spotifyplaylist.service.SongService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HttpSession httpSession;
    private final SongService songService;

    @GetMapping(value = {"/index", "/"})
    public String index() {
        if (httpSession.getAttribute("loggedIn") != null) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home(){
        if (httpSession.getAttribute("loggedIn") == null) {
            return new ModelAndView("redirect:/");
        }

        ModelAndView modelAndView = new ModelAndView("home");

        HomeViewModel homeViewModel = songService.getHomeViewModel();
        modelAndView.addObject("homeViewModel", homeViewModel);

        return modelAndView;
    }
}
