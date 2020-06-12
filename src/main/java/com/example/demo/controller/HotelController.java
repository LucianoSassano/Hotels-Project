package com.example.demo.controller;


import com.example.demo.dto.hotel.HotelDtoInput;
import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.model.Hotel;
import com.example.demo.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping(path = "/hotels")
@RestController
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody HotelDtoInput hotelDtoInput) {

        HotelDtoOutput newHotel = hotelService.add(hotelDtoInput);

        return ResponseEntity.ok(newHotel);
    }

    @GetMapping
    public ResponseEntity selectAll() {
        List<HotelDtoOutput> hotels = hotelService.getAll();

        return ResponseEntity.ok(hotels);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectById(@PathVariable Long id) {
        Hotel selectedHotel = hotelService.getById(id);

        return ResponseEntity.ok(new HotelDtoOutput(selectedHotel));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity replace(@PathVariable Long id, @Valid @RequestBody HotelDtoInput hotelDtoInput) {

        HotelDtoOutput updatedHotel = hotelService.replace(id, hotelDtoInput);

        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Hotel deleted = hotelService.delete(id);

        return ResponseEntity.ok(new HotelDtoOutput(deleted));
    }

}
