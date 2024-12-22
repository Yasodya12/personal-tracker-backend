package com.yasodya12.personal_tracker.service.impl;

import com.yasodya12.personal_tracker.entity.Category;
import com.yasodya12.personal_tracker.repositry.CategoryRepo;
import com.yasodya12.personal_tracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryServiceImpl implements CategoryService {


    private CategoryRepo categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategoriesByUserId(Long userId) {
        return categoryRepository.findByUserUserId(userId);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
