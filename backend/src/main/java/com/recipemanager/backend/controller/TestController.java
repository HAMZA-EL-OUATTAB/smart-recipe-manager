package com.recipemanager.backend.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/test")
    public Map<String,Object> test(){
        Map<String,Object> response = new HashMap<>();
        response.put("status","success");
        response.put("message","Backend is running");
        response.put("timestamp",LocalDateTime.now());
        response.put("developer","Hamza EL Ouattab");
        return response;
    }

    @GetMapping("/health")
    public Map<String,Object> health(){
        Map<String,Object> response = new HashMap<>();
        response.put("status","UP");
        response.put("service","Recipe Manager API");
        return response;
    }



}
