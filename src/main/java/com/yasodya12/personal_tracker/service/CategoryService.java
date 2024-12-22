package com.yasodya12.personal_tracker.service;

import com.yasodya12.personal_tracker.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getCategoriesByUserId(Long userId);
    void deleteCategory(Long categoryId);
}
