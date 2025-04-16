package app.web;

import app.message.service.MessageService;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.CreateNewMessage;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/messages")
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;

    @GetMapping("/new-message")
    public ModelAndView getNewMessagePage(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-message");
        modelAndView.addObject("user", user);
        modelAndView.addObject("createNewMessage", new CreateNewMessage());
        modelAndView.addObject("users", userService.getAllUsers());

        return modelAndView;
    }

    @PostMapping("/new-message")
    public ModelAndView createNewMessage(@Valid CreateNewMessage createNewMessage, BindingResult bindingResult, HttpSession session) {

        UUID userId = (UUID) session.getAttribute("user_id");
        User user = userService.getById(userId);

        if (bindingResult.hasErrors()) {

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("new-message");
            modelAndView.addObject("user", user);

            return modelAndView;
        }

        messageService.create(createNewMessage);

        return new ModelAndView("redirect:/home");
    }

    @DeleteMapping("/{id}")
    public String deleteSentMessage(@PathVariable("id") UUID messageId) {
        messageService.delete(messageId);
        return "redirect:/home";
    }

    @DeleteMapping("/received/{id}")
    public String deleteReceivedMessage(@PathVariable("id") UUID messageId) {
        messageService.delete(messageId);
        return "redirect:/home";
    }

}
