package com.example.andreys.web;

import com.example.andreys.model.view.ItemViewModel;
import com.example.andreys.service.ItemService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final ItemService itemService;

    @GetMapping("/")
    public String index(HttpSession httpSession) {

        if (httpSession.getAttribute("user") != null) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(HttpSession httpSession, Model model) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        List<ItemViewModel> allItems = this.itemService.findAllItems();
        model.addAttribute("items", allItems);

        return "home";
    }

}
