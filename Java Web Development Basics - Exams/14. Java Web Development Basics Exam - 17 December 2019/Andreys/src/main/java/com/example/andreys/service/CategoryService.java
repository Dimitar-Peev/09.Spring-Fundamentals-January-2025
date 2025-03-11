package com.example.andreys.service;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.entity.CategoryName;

public interface CategoryService {

    void initCategories();

    Category findByCategoryName(CategoryName name);
}
