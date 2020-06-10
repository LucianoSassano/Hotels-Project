package com.example.demo.controller;


import com.example.demo.dto.hotel.HotelDtoInput;
import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.model.Hotel;
import com.example.demo.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@RequestMapping(path = "/hotels")
@RestController
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity add(@RequestBody HotelDtoInput hotelDtoInput) {

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
        Optional<Hotel> selectedHotel = hotelService.getById(id);
//    selectedHotel.orElseThrow(() -> new NotFoundException(Constants.ROOM_NOT_FOUND));   //to do

        return ResponseEntity.ok(new HotelDtoOutput(selectedHotel.get()));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity replace(@PathVariable Long id, @RequestBody HotelDtoInput hotelDtoInput) {

        HotelDtoOutput updatedHotel = hotelService.replace(id, hotelDtoInput);

        //to do  Implement exceptions for not found cases (on service layer)
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Optional<Hotel> deleted = hotelService.delete(id);
        if (!deleted.isPresent()) {
            Map<String, String> result = new HashMap<String, String>();
//        result.put("message", Constants.ROOM_NOT_FOUND);       // to do   Is this way ok or is it better handled with exceptions?

            return ResponseEntity.status(404).body(result);
        }

        return ResponseEntity.ok(new HotelDtoOutput(deleted.get()));
    }

}
