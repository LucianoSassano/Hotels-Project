package com.example.demo.controller;

import com.example.demo.service.CreditCardService;
import com.example.demo.util.CardsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CreditCardController {
  @Autowired CreditCardService creditCardService;

  @GetMapping("")
  public ResponseEntity getAll() {
    return ResponseEntity.ok(CardsUtils.convertToListDTO(creditCardService.findAll()));
  }

  @GetMapping("/{number}")
  public ResponseEntity getByNumber(@PathVariable("number") Long number){
    return ResponseEntity.ok().body(creditCardService.findByNumber(number));
  }

  @DeleteMapping("/{number}")
  public void delete(@PathVariable("number") Long number){
    creditCardService.delete(number);
  }
}
