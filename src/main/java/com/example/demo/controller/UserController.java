package com.example.demo.controller;

import com.example.demo.dto.CardDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.CreditCardService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

  final UserService userService;
  final CreditCardService creditCardService;

  public UserController(UserService userService, CreditCardService creditCardService) {
    this.userService = userService;
    this.creditCardService = creditCardService;
  }

  @GetMapping("")
  public ResponseEntity getAll() {
    return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity getById(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(userService.findById(id));
  }

  @GetMapping("/{dni}/card")
  public ResponseEntity getCardsByDni(@PathVariable("dni") Integer dni) throws Exception {
    return ResponseEntity.ok().body(userService.findCardsByDni(dni));
  }

  @PostMapping("")
  public ResponseEntity insert(@RequestBody @Valid UserDTO userNew) {
    return ResponseEntity.ok().body(userService.insert(userNew));
  }

  @PostMapping("/{dni}/card")
  public ResponseEntity insert(@PathVariable("dni") Integer dni, @RequestBody @Valid CardDTO card) {
    return ResponseEntity.ok().body(userService.insertCard(card, dni));
  }

  @PutMapping("")
  public ResponseEntity updateByDni(@RequestBody @Valid UserDTO toUpdate, Integer dni) {
    return ResponseEntity.ok().body(userService.update(toUpdate, dni));
  }

  @DeleteMapping("/{dni}")
  public void delete(@PathVariable("dni") Integer dni) {
    userService.delete(dni);
  }
}
