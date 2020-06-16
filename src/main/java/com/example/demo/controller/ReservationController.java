package com.example.demo.controller;


import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.ReservationDtoOutput;
import com.example.demo.model.Reservation;
import com.example.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping(path = "/reservations")
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody ReservationDtoInput reservationDtoInput) {
        return ResponseEntity.ok(reservationService.add(reservationDtoInput));
    }

    @GetMapping
    public ResponseEntity<?> selectAll() {
        return ResponseEntity.ok(reservationService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> selectById(@PathVariable Long id) {
        return ResponseEntity.ok(new ReservationDtoOutput(reservationService.getById(id)));
    }

    @GetMapping(path = "/hotel/{id}")
    public ResponseEntity<?> selectByHotelId(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getByHotelId(id));
    }

    @GetMapping(path = "/room/{id}")
    public ResponseEntity<?> selectByRoomId(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getByRoomId(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> replace(@PathVariable Long id, @Valid @RequestBody ReservationDtoInput reservationDtoInput) {
        return ResponseEntity.ok(reservationService.replace(id, reservationDtoInput));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(new ReservationDtoOutput(reservationService.delete(id)));
    }

}
