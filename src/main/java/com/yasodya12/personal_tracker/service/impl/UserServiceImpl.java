package com.yasodya12.personal_tracker.service.impl;

import com.yasodya12.personal_tracker.entity.User;
import com.yasodya12.personal_tracker.repositry.UserRepo;
import com.yasodya12.personal_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {


    private UserRepo userRepository;

    @Autowired
    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        // Add password hashing or validation logic if needed
        return userRepository.save(user);
    }

    @Override
    public User authenticateUser(String email, String password) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}