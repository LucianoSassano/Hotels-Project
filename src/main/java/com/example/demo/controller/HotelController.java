package com.example.demo.controller;

import com.example.demo.dto.hotel.HotelDtoInput;
import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.dto.hotel.UncheckedHotel;
import com.example.demo.service.HotelService;
import com.example.demo.util.HotelUtils;
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
@RequestMapping(path = "/hotels")
@RestController
public class HotelController {

  private final HotelService hotelService;

  @PostMapping
  public ResponseEntity<HotelDtoOutput> add(@Valid @RequestBody HotelDtoInput hotelDtoInput) {
    return ResponseEntity.ok(new HotelDtoOutput(hotelService.add(hotelDtoInput)));
  }

  @GetMapping
  public ResponseEntity<List<HotelDtoOutput>> selectAll() {
    return ResponseEntity.ok(HotelUtils.listEntityToDTO(hotelService.getAll()));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<HotelDtoOutput> selectById(@PathVariable Long id) {
    return ResponseEntity.ok(new HotelDtoOutput(hotelService.getById(id)));
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<HotelDtoOutput> replace(
      @PathVariable Long id, @Valid @RequestBody HotelDtoInput hotelDtoInput) {
    return ResponseEntity.ok(new HotelDtoOutput(hotelService.replace(id, hotelDtoInput)));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<HotelDtoOutput> delete(@PathVariable Long id) {
    return ResponseEntity.ok(new HotelDtoOutput(hotelService.delete(id)));
  }

  @PatchMapping(path = "/{id}")
  public ResponseEntity<HotelDtoOutput> partialUpdate(
      @PathVariable Long id, @Valid @RequestBody UncheckedHotel uncheckedHotel) {
    return ResponseEntity.ok(new HotelDtoOutput(hotelService.partialUpdate(id, uncheckedHotel)));
  }
}
