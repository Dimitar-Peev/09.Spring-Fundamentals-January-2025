package coffeeshop.model.view;

import coffeeshop.model.entity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderViewModel {

    private String id;
    private String name;
    private BigDecimal price;
    private Category category;
    private int neededTime;
}
