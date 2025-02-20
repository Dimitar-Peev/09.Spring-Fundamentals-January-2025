package com.paintingscollectors.web.controller;

import com.paintingscollectors.constants.Constants;
import com.paintingscollectors.model.dto.PaintingAddBindingModel;
import com.paintingscollectors.model.service.PaintingServiceModel;
import com.paintingscollectors.service.PaintingService;
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
@RequestMapping("/paintings")
@AllArgsConstructor
public class PaintingController {

    private final PaintingService paintingService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("paintingAddBindingModel")) {
            model.addAttribute("paintingAddBindingModel", new PaintingAddBindingModel());
        }

        return "add-painting";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid PaintingAddBindingModel paintingAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("paintingAddBindingModel", paintingAddBindingModel);
            redirectAttributes.addFlashAttribute(Constants.BINDING_MODEL + "paintingAddBindingModel", bindingResult);
            return "redirect:add";
        }

        PaintingServiceModel paintingServiceModel = this.modelMapper.map(paintingAddBindingModel, PaintingServiceModel.class);

        boolean success = this.paintingService.add(paintingServiceModel);
        if (!success) {
            redirectAttributes.addFlashAttribute("paintingAddBindingModel", paintingAddBindingModel);
            return "redirect:/add";
        }

        return "redirect:/home";
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable String id) {

        paintingService.remove(id);

        return "redirect:/home";
    }

    @PostMapping("/addToFavorites/{id}")
    public String addToFavorites(@PathVariable String id) {

        String username = httpSession.getAttribute("username").toString();
        paintingService.addToFavorites(id, username);

        return "redirect:/home";
    }

    @GetMapping("/removeFromFavorites/{id}")
    public String removeFromFavorites(@PathVariable String id) {

        String username = httpSession.getAttribute("username").toString();
        paintingService.removeFromFavorites(id, username);

        return "redirect:/home";
    }

    @PostMapping("/votePainting/{id}")
    public String votePainting(@PathVariable String id) {

        String username = httpSession.getAttribute("username").toString();
        paintingService.votePainting(id, username);

        return "redirect:/home";
    }
}
