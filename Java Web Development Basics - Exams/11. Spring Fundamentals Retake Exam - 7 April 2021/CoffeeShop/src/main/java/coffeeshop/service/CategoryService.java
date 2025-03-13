package coffeeshop.service;

import coffeeshop.model.entity.Category;
import coffeeshop.model.entity.CategoryName;

public interface CategoryService {

    void initCategories();

    Category findByCategoryName(CategoryName categoryName);
}
