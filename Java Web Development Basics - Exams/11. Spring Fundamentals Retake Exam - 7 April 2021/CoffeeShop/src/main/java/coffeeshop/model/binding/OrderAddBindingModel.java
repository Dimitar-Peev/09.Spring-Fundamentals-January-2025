package coffeeshop.model.binding;

import coffeeshop.model.entity.CategoryName;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderAddBindingModel {

    @NotBlank(message = "")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20")
    private String name;

    @NotNull(message = "The price must be positive")
    @Positive(message = "The price must be positive")
    private BigDecimal price;

    @PastOrPresent(message = "Order time cannot be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;

    @NotNull(message = "You must select the category")
    private CategoryName category;

    @NotNull
    @Size(min = 5, message = "Description size must be minimum 5 char")
    private String description;
}
