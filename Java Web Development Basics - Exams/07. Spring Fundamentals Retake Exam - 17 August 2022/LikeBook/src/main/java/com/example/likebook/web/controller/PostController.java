package com.example.likebook.web.controller;

import com.example.likebook.constant.Constants;
import com.example.likebook.model.binding.PostAddBindingModel;
import com.example.likebook.model.service.PostServiceModel;
import com.example.likebook.service.PostService;
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
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("postAddBindingModel")) {
            model.addAttribute("postAddBindingModel", new PostAddBindingModel());
        }

        return "post-add";
    }


    @PostMapping("/add")
    public String addConfirm(@Valid PostAddBindingModel postAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postAddBindingModel", postAddBindingModel);
            redirectAttributes.addFlashAttribute(Constants.BINDING_MODEL + "postAddBindingModel", bindingResult);
            return "redirect:add";
        }

        PostServiceModel postServiceModel = this.modelMapper.map(postAddBindingModel, PostServiceModel.class);
        postServiceModel.setUserId(httpSession.getAttribute("user_id").toString());

        boolean success = this.postService.add(postServiceModel);
        if (!success) {
            redirectAttributes.addFlashAttribute("postAddBindingModel", postAddBindingModel);
            return "redirect:/add";
        }

        return "redirect:/home";
    }

    @GetMapping("/like/{id}")
    public String likePost(@PathVariable String id) {

        postService.likePost(id, httpSession.getAttribute("user_id").toString());

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String deletePost(@PathVariable String id) {

        postService.removePost(id);

        return "redirect:/home";
    }
}
