package com.example.spotifyplaylist.web.controller;


import com.example.spotifyplaylist.constant.Constants;
import com.example.spotifyplaylist.model.binding.SongAddBindingModel;
import com.example.spotifyplaylist.model.service.SongServiceModel;
import com.example.spotifyplaylist.service.SongService;
import com.example.spotifyplaylist.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService stampService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/add")
    public String add(Model model) {
        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("songAddBindingModel")) {
            model.addAttribute("songAddBindingModel", new SongAddBindingModel());
        }

        return "song-add";
    }


    @PostMapping("/add")
    public String addConfirm(@Valid SongAddBindingModel songAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (httpSession.getAttribute("loggedIn") == null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("songAddBindingModel", songAddBindingModel);
            redirectAttributes.addFlashAttribute(Constants.BINDING_MODEL + "songAddBindingModel", bindingResult);
            return "redirect:add";
        }

        SongServiceModel songServiceModel = this.modelMapper.map(songAddBindingModel, SongServiceModel.class);

        String username = httpSession.getAttribute("username").toString();
        boolean success = this.stampService.add(songServiceModel,  username);
        if (!success) {
            redirectAttributes.addFlashAttribute("songAddBindingModel", songAddBindingModel);
            return "redirect:/add";
        }

        return "redirect:/home";
    }



}
