package com.example.andreys.repository;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findCategoryByName(CategoryName name);
}
