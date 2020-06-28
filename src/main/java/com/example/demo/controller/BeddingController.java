package com.example.demo.controller;

import com.example.demo.dto.bedding.BeddingDto;
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
  public ResponseEntity<BeddingDto> add(@Valid @RequestBody BeddingDto beddingDto) {
    return ResponseEntity.ok(new BeddingDto(beddingService.add(beddingDto)));
  }

  @GetMapping
  public ResponseEntity<List<BeddingDto>> selectAll() {
    return ResponseEntity.ok(BeddingUtils.listEntityToDTO(beddingService.getAll()));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<BeddingDto> selectById(@PathVariable Long id) {
    return ResponseEntity.ok(new BeddingDto(beddingService.getById(id)));
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<BeddingDto> replace(
      @PathVariable Long id, @Valid @RequestBody BeddingDto beddingDto) {
    return ResponseEntity.ok(new BeddingDto(beddingService.replace(id, beddingDto)));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<BeddingDto> delete(@PathVariable Long id) {
    return ResponseEntity.ok(new BeddingDto(beddingService.delete(id)));
  }

  @PatchMapping(path = "/{id}")
  public ResponseEntity<BeddingDto> partialUpdate(
      @PathVariable Long id, @Valid @RequestBody UncheckedBedding uncheckedBedding) {
    return ResponseEntity.ok(new BeddingDto(beddingService.partialUpdate(id, uncheckedBedding)));
  }
}
