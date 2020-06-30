package com.example.demo.controller;

import com.example.demo.dto.state.EstateInputDto;
import com.example.demo.dto.state.EstateOutputDto;
import com.example.demo.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/states")
@RestController
public class StateController {

  @Autowired private StateService stateService;

  @GetMapping
  public ResponseEntity getAllStates() {

    return ResponseEntity.ok().body(stateService.listAllStates());
  }

  @GetMapping(path = "{id}")
  public ResponseEntity getStateById(@PathVariable Long id) {

    return ResponseEntity.ok().body(new EstateOutputDto(stateService.getById(id)));
  }

  @PostMapping
  public ResponseEntity createState(@RequestBody EstateInputDto estateDto) {
    return ResponseEntity.ok().body(stateService.add(estateDto));
  }

  @PutMapping(path = "{id}")
  public ResponseEntity updateState(@PathVariable Long id, @RequestBody EstateInputDto estateDto) {

    return ResponseEntity.ok().body(stateService.updateState(id, estateDto));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteState(@PathVariable Long id) {

    return ResponseEntity.ok().body(stateService.delete(id));
  }
}
