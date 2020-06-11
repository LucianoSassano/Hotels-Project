package com.example.demo.controller;


import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity getAll(){
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());


    }
    @PostMapping("")
    public ResponseEntity insert(@RequestBody UserDTO userNew){

        return ResponseEntity.status(HttpStatus.OK).body(userService.insert(userNew));
    }
    /*@GetMapping ("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){

    }*/

}
