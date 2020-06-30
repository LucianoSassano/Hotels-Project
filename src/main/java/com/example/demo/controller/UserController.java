package com.example.demo.controller;

import com.example.demo.dto.CardDTO;
import com.example.demo.dto.UserCardsDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDtoInsert;
import com.example.demo.service.CreditCardService;
import com.example.demo.service.UserService;
import com.example.demo.util.UserUtils;
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
import java.util.List;

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
    List<UserDTO> result = UserUtils.listEntityToDTO(userService.findAll());
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity getById(@PathVariable("id") Long id) {
    return ResponseEntity.ok().body(UserDTO.generateInstanceFromUser(userService.findById(id)));
  }

  @GetMapping("/{dni}/card")
  public ResponseEntity getCardsByDni(@PathVariable("dni") Integer dni) {
    return ResponseEntity.ok().body(UserCardsDTO.generateInstanceByUser(userService.findUserWithCardsByDni(dni)));
  }

  @PostMapping("")
  public ResponseEntity insert(@RequestBody @Valid UserDtoInsert userNew) {
    return ResponseEntity.ok().body(UserDTO.generateInstanceFromUser(userService.insert(userNew)));
  }

  @PostMapping("/{dni}/card")
  public ResponseEntity insert(@PathVariable("dni") Integer dni, @RequestBody @Valid CardDTO card) {
  return ResponseEntity.ok().body(CardDTO.generateInstanceFromEntity(userService.insertCard(card, dni)));
  }

  @PutMapping("")
  public ResponseEntity updateByDni(@RequestBody @Valid UserDTO toUpdate, Integer dni) {
    return ResponseEntity.ok().body(UserDTO.generateInstanceFromUser(userService.update(toUpdate, dni)));
  }

  @DeleteMapping("/{dni}")
  public ResponseEntity delete(@PathVariable("dni") Integer dni) {

    return ResponseEntity.ok().body(UserDTO.generateInstanceFromUser(userService.delete(dni)));
  }
}
