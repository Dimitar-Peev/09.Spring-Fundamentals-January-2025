package com.example.spotifyplaylist.web.controller;

import com.example.spotifyplaylist.constant.Constants;
import com.example.spotifyplaylist.model.binding.UserLoginBindingModel;
import com.example.spotifyplaylist.model.binding.UserRegisterBindingModel;
import com.example.spotifyplaylist.model.service.UserServiceModel;
import com.example.spotifyplaylist.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    @GetMapping("/register")
    public String register(Model model) {
        if (httpSession.getAttribute("loggedIn") != null) {
            return "redirect:/";
        }

        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("isExists", false);
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (httpSession.getAttribute("loggedIn") != null) {
            return "redirect:/";
        }

        boolean passwordMatch = userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
        if (bindingResult.hasErrors() || !passwordMatch) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute(Constants.BINDING_MODEL + "userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        boolean isSaved = this.userService.register(userServiceModel);
        if (!isSaved) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("isExists", true);
            return "redirect:register";
        }

        return "redirect:login";
    }


    @GetMapping("/login")
    public String login(Model model) {
        if (httpSession.getAttribute("loggedIn") != null) {
            return "redirect:/";
        }

        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (httpSession.getAttribute("loggedIn") != null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute(Constants.BINDING_MODEL + "userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userLoginBindingModel, UserServiceModel.class);

        boolean isLogged = this.userService.login(userServiceModel);
        if (!isLogged) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:login";
        }

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout() {
        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        this.userService.logout();
        return "redirect:/";
    }

    @GetMapping("/playlist/add/{id}")
    public String addToPlaylist(@PathVariable String id) {
        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        String username = (String) httpSession.getAttribute("username");
        userService.addToPlaylist(username, id);
        return "redirect:/home";
    }

    @GetMapping("/playlist/clear")
    public String removeFromWishlist() {
        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        userService.clearPlaylist();
        return "redirect:/home";
    }
}
