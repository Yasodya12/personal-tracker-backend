package com.yasodya12.personal_tracker.repositry;

import com.yasodya12.personal_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
