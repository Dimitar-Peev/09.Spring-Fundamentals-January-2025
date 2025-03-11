package com.example.andreys.model.service;

import com.example.andreys.model.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ItemServiceModel{

    private String id;
    private String name;
    private String description;
    private String gender;
    private BigDecimal price;
    private Category category;
}