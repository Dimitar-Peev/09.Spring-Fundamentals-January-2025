package app.web;

import app.user.model.User;
import app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final UserService userService;

    @Autowired
    public SubscriptionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUpgradePage() {

        return "upgrade";
    }

    @GetMapping("/history")
    public ModelAndView getUserSubscriptions() {

        ModelAndView modelAndView = new ModelAndView("subscription-history");

        User userById = userService.getById(UUID.fromString("cd8e1d31-53e4-4cf1-9df6-851a32c613db"));

        modelAndView.addObject("user", userById);

        return modelAndView;
    }
}
