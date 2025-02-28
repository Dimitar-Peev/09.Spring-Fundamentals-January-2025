package app.web;

import app.user.model.User;
import app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class IndexController {

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndexPage() {

        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage() {

        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage() {

        return "register";
    }

    @GetMapping("/home")
    public ModelAndView getHomePage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");

        User user = userService.getById(UUID.fromString("cd8e1d31-53e4-4cf1-9df6-851a32c613db"));
        modelAndView.addObject("user", user);

        return modelAndView;
    }

}
