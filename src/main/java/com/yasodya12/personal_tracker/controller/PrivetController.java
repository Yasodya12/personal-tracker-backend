package com.yasodya12.personal_tracker.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
@CrossOrigin
public class PrivetController {
    @GetMapping
    public String get(){
        System.out.println("private controller");
        return "OK";
    }
}
