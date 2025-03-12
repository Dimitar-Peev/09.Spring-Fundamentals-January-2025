package com.example.andreys.web;

import com.example.andreys.model.binding.ItemAddBindingModel;
import com.example.andreys.model.service.ItemServiceModel;
import com.example.andreys.model.view.ItemViewModel;
import com.example.andreys.service.ItemService;
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
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    @GetMapping("/add")
    public String add(Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("itemAddBindingModel")) {
            model.addAttribute("itemAddBindingModel", new ItemAddBindingModel());
        }
        return "add-item";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ItemAddBindingModel itemAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.itemAddBindingModel", bindingResult);
            return "redirect:add";
        }

        ItemServiceModel itemServiceModel = this.modelMapper.map(itemAddBindingModel, ItemServiceModel.class);

        boolean success = this.itemService.add(itemServiceModel);
        if (!success) {
            redirectAttributes.addFlashAttribute("itemAddBindingModel", itemAddBindingModel);
            return "redirect:/add";
        }

        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        ItemViewModel itemById = this.itemService.findItemById(id);
        model.addAttribute("item", itemById);

        return "details-item";
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {

        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }

        this.itemService.delete(id);

        return "redirect:/";
    }
}
