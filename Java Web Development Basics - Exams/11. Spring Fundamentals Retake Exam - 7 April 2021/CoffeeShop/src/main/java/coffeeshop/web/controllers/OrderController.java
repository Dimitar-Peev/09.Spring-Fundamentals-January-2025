package coffeeshop.web.controllers;

import coffeeshop.model.binding.OrderAddBindingModel;
import coffeeshop.model.service.OrderServiceModel;
import coffeeshop.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel(){
        return new OrderAddBindingModel();
    }

    @GetMapping("/add")
    public String add() {
        return "order-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid OrderAddBindingModel orderAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel", bindingResult);
            return "redirect:add";
        }

        OrderServiceModel orderServiceModel = this.modelMapper.map(orderAddBindingModel, OrderServiceModel.class);
        this.orderService.addOrder(orderServiceModel);

        return "redirect:/home";
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable String id){

        orderService.readyOrder(id);

        return "redirect:/home";
    }
}
