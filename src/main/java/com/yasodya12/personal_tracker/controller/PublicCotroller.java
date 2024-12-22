package com.yasodya12.personal_tracker.controller;


import com.yasodya12.personal_tracker.jwtUtil.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public String get(@RequestParam String name,@RequestParam String role){
        System.out.println(name);
        List<String> roles=new ArrayList();
        roles.add(role);
        String s = jwtTokenUtil.generateToken(name, roles);
        return s;
    }

}
