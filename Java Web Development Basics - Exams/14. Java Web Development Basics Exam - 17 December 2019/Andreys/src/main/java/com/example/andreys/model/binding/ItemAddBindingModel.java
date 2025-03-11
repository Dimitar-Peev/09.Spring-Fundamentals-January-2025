package com.example.andreys.model.binding;

import com.example.andreys.model.entity.CategoryName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ItemAddBindingModel {

    @Size(min = 2, message = "Name length must be more than two characters")
    private String name;

    @Size(min = 3, message = "Description length must be more than three characters")
    private String description;

    @NotNull(message = "Enter valid category name")
    private CategoryName category;

    @NotBlank(message = "Enter valid gender")
    private String gender;

    @NotNull(message = "")
    @PositiveOrZero(message = "Price must be positive number")
    private BigDecimal price;
}