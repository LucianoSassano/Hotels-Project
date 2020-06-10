package com.example.demo.controller;


import com.example.demo.dto.bedding.BeddingDto;
import com.example.demo.model.Bedding;
import com.example.demo.service.BeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@RequestMapping(path = "/beddings")
@RestController
public class BeddingController {

    private final BeddingService beddingService;

    @PostMapping
    public ResponseEntity add(@RequestBody BeddingDto beddingDto) {
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
        Optional<Bedding> selectedBedding = beddingService.getById(id);
//    selectedBedding.orElseThrow(() -> new NotFoundException(Constants.BEDDING_NOT_FOUND));   //to do

        return ResponseEntity.ok(new BeddingDto(selectedBedding.get()));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity replace(@PathVariable Long id, @RequestBody BeddingDto beddingDto) {

        Optional<Bedding> updated = beddingService.replace(id, beddingDto);
        if (!updated.isPresent()) {
            Map<String, String> result = new HashMap<String, String>();
//        result.put("message", Constants.BEDDING_NOT_FOUND);       //to do   Is this way ok or is it better handled with exceptions?

            return ResponseEntity.status(404).body(result);
        }

        return ResponseEntity.ok(new BeddingDto(updated.get()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Optional<Bedding> deleted = beddingService.delete(id);
        if (!deleted.isPresent()) {
            Map<String, String> result = new HashMap<String, String>();
//        result.put("message", Constants.BEDDING_NOT_FOUND);       // to do   Is this way ok or is it better handled with exceptions?

            return ResponseEntity.status(404).body(result);
        }

        return ResponseEntity.ok(new BeddingDto(deleted.get()));
    }

}
