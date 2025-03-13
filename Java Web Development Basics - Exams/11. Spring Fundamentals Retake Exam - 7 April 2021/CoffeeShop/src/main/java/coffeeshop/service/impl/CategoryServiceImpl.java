package coffeeshop.service.impl;

import coffeeshop.model.entity.Category;
import coffeeshop.model.entity.CategoryName;
import coffeeshop.repository.CategoryRepository;
import coffeeshop.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void initCategories() {

        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        Category category = new Category();
                        category.setName(categoryName);
                        switch (categoryName){
                            case CAKE -> category.setNeededTime(10);
                            case COFFEE -> category.setNeededTime(2);
                            case DRINK -> category.setNeededTime(1);
                            case OTHER -> category.setNeededTime(5);
                        }

                        categoryRepository.save(category);
                    });
        }
    }

    @Override
    public Category findByCategoryName(CategoryName categoryName) {
        return this.categoryRepository.findByName(categoryName).orElse(null);
    }
}
