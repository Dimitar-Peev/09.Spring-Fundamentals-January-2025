package coffeeshop.service;

import coffeeshop.model.service.OrderServiceModel;
import coffeeshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {

    void addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrdersOrderByPriceDesc();

    void readyOrder(String id);
}
