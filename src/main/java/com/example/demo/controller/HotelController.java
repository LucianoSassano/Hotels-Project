package com.example.demo.controller;

import com.example.demo.dto.hotel.HotelDtoInput;
import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.service.HotelService;
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
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path = "/hotels")
@RestController
public class HotelController {

  private final HotelService hotelService;

  @PostMapping
  public ResponseEntity add(@Valid @RequestBody HotelDtoInput hotelDtoInput) {
    return ResponseEntity.ok(hotelService.add(hotelDtoInput));
  }

  @GetMapping
  public ResponseEntity selectAll() {
    return ResponseEntity.ok(hotelService.getAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity selectById(@PathVariable Long id) {
    return ResponseEntity.ok(new HotelDtoOutput(hotelService.getById(id)));
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity replace(
      @PathVariable Long id, @Valid @RequestBody HotelDtoInput hotelDtoInput) {
    return ResponseEntity.ok(hotelService.replace(id, hotelDtoInput));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    return ResponseEntity.ok(new HotelDtoOutput(hotelService.delete(id)));
  }
}
