package com.example.demo.controller;

import com.example.demo.dto.bedding.BeddingDto;
import com.example.demo.service.BeddingService;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@RequestMapping(path = "/beddings")
@RestController
public class BeddingController {

  private final BeddingService beddingService;

  @PostMapping
  public ResponseEntity add(@Valid @RequestBody BeddingDto beddingDto) {
    return ResponseEntity.ok(beddingService.add(beddingDto));
  }

  @GetMapping
  public ResponseEntity selectAll() {
    return ResponseEntity.ok(beddingService.getAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity selectById(@PathVariable Long id) {
    return ResponseEntity.ok(new BeddingDto(beddingService.getById(id)));
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity replace(@PathVariable Long id, @Valid @RequestBody BeddingDto beddingDto) {
    return ResponseEntity.ok(new BeddingDto(beddingService.replace(id, beddingDto)));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    return ResponseEntity.ok(new BeddingDto(beddingService.delete(id)));
  }
}
