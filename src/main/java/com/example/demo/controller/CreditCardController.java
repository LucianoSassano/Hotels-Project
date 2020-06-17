package com.example.demo.controller;

import com.example.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Controller to Card request management.
@RestController
@RequestMapping("/cards")
public class CreditCardController {
  @Autowired CreditCardService creditCardService;

  // This endpoint return all cards registered.
  @GetMapping("")
  public ResponseEntity getAll() {
    return ResponseEntity.ok(creditCardService.findAll());
  }

  // This endpoint return a specific card information,searching by number.
  @GetMapping("/{number}")
  public ResponseEntity getByNumber(@PathVariable("number") Long number) throws Exception {
    return ResponseEntity.ok().body(creditCardService.findByNumber(number));
  }

  // This endpoint make a soft-delete to specific card.
  @DeleteMapping("/{number}")
  public void delete(@PathVariable("number") Long number) throws Exception {
    creditCardService.delete(number);
  }
}
