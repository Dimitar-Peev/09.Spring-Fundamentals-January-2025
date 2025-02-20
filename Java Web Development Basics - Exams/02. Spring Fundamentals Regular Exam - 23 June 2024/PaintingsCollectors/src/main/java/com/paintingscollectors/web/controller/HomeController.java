package com.paintingscollectors.web.controller;

import com.paintingscollectors.model.view.HomeViewModel;
import com.paintingscollectors.service.PaintingService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class HomeController {

    private final PaintingService paintingService;
    private final HttpSession httpSession;

    @GetMapping(value = {"/index", "/"})
    public String index() {

        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView("home");

        String username = httpSession.getAttribute("username").toString();
        modelAndView.addObject("username", username);

        HomeViewModel homeViewModel = paintingService.getHomeViewModel();
        modelAndView.addObject("homeViewModel", homeViewModel);

        return modelAndView;
    }
}
