package com.yasodya12.personal_tracker.service;

import com.yasodya12.personal_tracker.entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    User authenticateUser(String email, String password);
    User getUserById(Long userId);
}
