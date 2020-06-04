package com.example.demo.controller;


import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("")
    public void findAll(){
        User u = new User();

    }
}
