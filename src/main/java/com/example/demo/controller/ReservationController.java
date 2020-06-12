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
    public ResponseEntity add(@Valid @RequestBody ReservationDtoInput reservationDtoInput) {

        ReservationDtoOutput newReservation = reservationService.add(reservationDtoInput);

        return ResponseEntity.ok(newReservation);
    }

    @GetMapping
    public ResponseEntity selectAll() {
        List<ReservationDtoOutput> reservations = reservationService.getAll();

        return ResponseEntity.ok(reservations);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectById(@PathVariable Long id) {
        Reservation selectedReservation = reservationService.getById(id);

        return ResponseEntity.ok(new ReservationDtoOutput(selectedReservation));
    }

    @GetMapping(path = "/hotel/{id}")
    public ResponseEntity selectByHotelId(@PathVariable Long id) {
        List<ReservationDtoOutput> reservations = reservationService.getByHotelId(id);

        return ResponseEntity.ok(reservations);
    }

    @GetMapping(path = "/room/{id}")
    public ResponseEntity selectByRoomId(@PathVariable Long id) {
        List<ReservationDtoOutput> reservations = reservationService.getByRoomId(id);

        return ResponseEntity.ok(reservations);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity replace(@PathVariable Long id, @Valid @RequestBody ReservationDtoInput reservationDtoInput) {

        ReservationDtoOutput updatedReservation = reservationService.replace(id, reservationDtoInput);

        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Reservation deleted = reservationService.delete(id);

        return ResponseEntity.ok(new ReservationDtoOutput(deleted));
    }

}
