package com.example.demo.controller;

import com.example.demo.dto.bedding.BeddingDto;
import com.example.demo.model.Bedding;
import com.example.demo.service.BeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
