package com.yasodya12.personal_tracker.controller;


import com.yasodya12.personal_tracker.jwtUtil.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@CrossOrigin
public class PublicCotroller {

    JwtTokenUtil jwtTokenUtil;

    @Autowired
    public PublicCotroller(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping
    public String get(@RequestParam String name){
        System.out.println(name);
        String s = jwtTokenUtil.generateToken(name);
        return s;
    }

}
