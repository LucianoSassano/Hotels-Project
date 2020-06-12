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
        BeddingDto newBedding = beddingService.add(beddingDto);

        return ResponseEntity.ok(newBedding);
    }

    @GetMapping
    public ResponseEntity selectAll() {
        List<BeddingDto> beddings = beddingService.getAll();

        return ResponseEntity.ok(beddings);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectById(@PathVariable Long id) {
        Bedding selectedBedding = beddingService.getById(id);

        return ResponseEntity.ok(new BeddingDto(selectedBedding));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity replace(@PathVariable Long id, @Valid @RequestBody BeddingDto beddingDto) {

        Bedding updated = beddingService.replace(id, beddingDto);

        return ResponseEntity.ok(new BeddingDto(updated));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Bedding deleted = beddingService.delete(id);

        return ResponseEntity.ok(new BeddingDto(deleted));
    }

}
