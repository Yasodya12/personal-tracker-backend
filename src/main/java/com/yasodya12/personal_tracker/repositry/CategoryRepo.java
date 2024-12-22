package com.yasodya12.personal_tracker.repositry;

import com.yasodya12.personal_tracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface CategoryRepo extends JpaRepository<Category, Long> {
   List<Category> findByUserUserId(Long id);
}
