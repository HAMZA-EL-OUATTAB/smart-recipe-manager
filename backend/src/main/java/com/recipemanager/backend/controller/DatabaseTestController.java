package com.recipemanager.backend.controller;

import com.recipemanager.backend.model.User;
import com.recipemanager.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/database")
public class DatabaseTestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public Map<String, Object> testDatabase() {
        Map<String, Object> response = new HashMap<>();

        long userCount = userRepository.count();

        response.put("status", "success");
        response.put("message", "Database connection working!");
        response.put("userCount", userCount);
        response.put("database", "PostgreSQL");
        response.put("tablesCreated", "users, recipes, nutrition_info");

        return response;
    }

    @PostMapping("/test-user")
    public Map<String, Object> createTestUser() {
        Map<String, Object> response = new HashMap<>();

        try {
            if (userRepository.existsByEmail("test@example.com")) {
                response.put("status", "info");
                response.put("message", "Test user already exists!");
                User existingUser = userRepository.findByEmail("test@example.com").get();
                response.put("userId", existingUser.getId());
                response.put("email", existingUser.getEmail());
                return response;
            }

            User user = new User();
            user.setEmail("test@example.com");
            user.setPassword("password123");
            user.setFirstName("Test");
            user.setLastName("User");
            user.setIsActive(true);

            User savedUser = userRepository.save(user);

            response.put("status", "success");
            response.put("message", "Test user created!");
            response.put("userId", savedUser.getId());
            response.put("email", savedUser.getEmail());
            response.put("firstName", savedUser.getFirstName());
            response.put("lastName", savedUser.getLastName());
            response.put("createdAt", savedUser.getCreatedAt());
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
        }

        return response;
    }

    @GetMapping("/users")
    public Map<String, Object> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        List<User> users = userRepository.findAll();
        response.put("status", "success");
        response.put("count", users.size());
        response.put("users", users);
        return response;
    }
}