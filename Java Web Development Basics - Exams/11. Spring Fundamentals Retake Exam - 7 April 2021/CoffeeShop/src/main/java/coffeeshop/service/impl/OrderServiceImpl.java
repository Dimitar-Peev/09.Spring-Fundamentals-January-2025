package coffeeshop.service.impl;

import coffeeshop.model.entity.Order;
import coffeeshop.model.service.OrderServiceModel;
import coffeeshop.model.view.OrderViewModel;
import coffeeshop.repository.OrderRepository;
import coffeeshop.service.CategoryService;
import coffeeshop.service.OrderService;
import coffeeshop.service.UserService;
import coffeeshop.util.CurrentUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;

    @Override
    public void addOrder(OrderServiceModel orderServiceModel) {
        Order order = this.modelMapper.map(orderServiceModel, Order.class);
        order.setEmployee(userService.findById(currentUser.getId()));
        order.setCategory(categoryService.findByCategoryName(orderServiceModel.getCategory().getName()));

        this.orderRepository.save(order);
    }

    @Override
    public List<OrderViewModel> findAllOrdersOrderByPriceDesc() {
        return this.orderRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(order -> this.modelMapper.map(order, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void readyOrder(String id) {
        this.orderRepository.deleteById(id);
    }

}
