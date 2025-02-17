package app.web;

import app.story.model.Story;
import app.story.service.StoryService;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.CreateNewStory;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/stories")
public class StoryController {

    private final UserService userService;
    private final StoryService storyService;

    @Autowired
    public StoryController(UserService userService, StoryService storyService) {
        this.userService = userService;
        this.storyService = storyService;
    }

    @GetMapping("/new")
    public ModelAndView getNewStoryPage(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-story");
        modelAndView.addObject("user", user);
        modelAndView.addObject("createNewStory", new CreateNewStory());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView createNewStory(@Valid CreateNewStory createNewStory, BindingResult bindingResult, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        if (bindingResult.hasErrors()) {

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("add-story");
            modelAndView.addObject("user", user);

            return modelAndView;
        }

        storyService.create(createNewStory, user);

        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/{id}/visibility")
    public String shareStory(@PathVariable UUID id) {

        storyService.share(id);

        return "redirect:/home";
    }

    @DeleteMapping("/{id}")
    public String deleteStory(@PathVariable UUID id) {

        storyService.deleteStoryById(id);

        return "redirect:/home";
    }

    @GetMapping("/{id}")
    public String readStory(@PathVariable UUID id, HttpSession session, Model model) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        Story story = storyService.getById(id);
        model.addAttribute("story", story);
        model.addAttribute("user", user);

        return "story";
    }
}