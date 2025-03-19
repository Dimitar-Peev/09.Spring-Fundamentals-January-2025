package coffeeshop.web.controllers;

import coffeeshop.model.view.OrderViewModel;
import coffeeshop.service.OrderService;
import coffeeshop.service.UserService;
import coffeeshop.util.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;


    @GetMapping(value = {"/index", "/"})
    public String index() {

        if (currentUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String index(Model model) {

        if (!currentUser.isLogged()) {
            return "redirect:/";
        }

        List<OrderViewModel> orders = orderService.findAllOrdersOrderByPriceDesc();

        int timeToReadyAll = orders.stream().mapToInt(OrderViewModel::getNeededTime).sum();

        model.addAttribute("orders", orderService.findAllOrdersOrderByPriceDesc());
        model.addAttribute("totalTime", timeToReadyAll);
        model.addAttribute("users", userService.findAllUsersAndCountOfOrdersOrderByCountDesc());

        return "home";
    }
}
