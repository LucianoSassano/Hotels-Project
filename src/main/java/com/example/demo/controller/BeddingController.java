package com.example.demo.controller;

import com.example.demo.dto.bedding.BeddingDtoInput;
import com.example.demo.dto.bedding.BeddingDtoOutput;
import com.example.demo.dto.bedding.UncheckedBedding;
import com.example.demo.service.BeddingService;
import com.example.demo.util.BeddingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/beddings")
@RestController
public class BeddingController {

  private final BeddingService beddingService;

  @PostMapping
  public ResponseEntity<BeddingDtoOutput> add(@Valid @RequestBody BeddingDtoInput beddingDtoInput) {
    return ResponseEntity.ok(new BeddingDtoOutput(beddingService.add(beddingDtoInput)));
  }

  @GetMapping
  public ResponseEntity<List<BeddingDtoOutput>> selectAll() {
    return ResponseEntity.ok(BeddingUtils.listEntityToDTO(beddingService.getAll()));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<BeddingDtoOutput> selectById(@PathVariable Long id) {
    return ResponseEntity.ok(new BeddingDtoOutput(beddingService.getById(id)));
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<BeddingDtoOutput> replace(
      @PathVariable Long id, @Valid @RequestBody BeddingDtoInput beddingDtoInput) {
    return ResponseEntity.ok(new BeddingDtoOutput(beddingService.replace(id, beddingDtoInput)));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<BeddingDtoOutput> delete(@PathVariable Long id) {
    return ResponseEntity.ok(new BeddingDtoOutput(beddingService.delete(id)));
  }

  @PatchMapping(path = "/{id}")
  public ResponseEntity<BeddingDtoOutput> partialUpdate(
      @PathVariable Long id, @Valid @RequestBody UncheckedBedding uncheckedBedding) {
    return ResponseEntity.ok(
        new BeddingDtoOutput(beddingService.partialUpdate(id, uncheckedBedding)));
  }
}
