package com.example.demo.controller;

import com.example.demo.repository.CreditCardRepository;
import com.example.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CreditCardController {
    @Autowired
    CreditCardService creditCardService;

    @GetMapping("")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(creditCardService.findAll());
    }

}
