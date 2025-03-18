package coffeeshop.model.service;

import coffeeshop.model.entity.Category;
import coffeeshop.model.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderServiceModel {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime orderTime;
    private Category category;
    private User user;
}
