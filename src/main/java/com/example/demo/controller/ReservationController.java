package com.example.demo.controller;


import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.ReservationDtoOutput;
import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.model.Reservation;
import com.example.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@RequestMapping(path = "/reservations")
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity add(@RequestBody ReservationDtoInput reservationDtoInput) {

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
        Optional<Reservation> selectedReservation = reservationService.getById(id);
//    selectedReservation.orElseThrow(() -> new NotFoundException(Constants.ROOM_NOT_FOUND));   //to do

        return ResponseEntity.ok(new ReservationDtoOutput(selectedReservation.get()));
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
    public ResponseEntity replace(@PathVariable Long id, @RequestBody ReservationDtoInput reservationDtoInput) {

        ReservationDtoOutput updatedReservation = reservationService.replace(id, reservationDtoInput);

        //to do  Implement exceptions for not found cases (on service layer)
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Optional<Reservation> deleted = reservationService.delete(id);
        if (!deleted.isPresent()) {
            Map<String, String> result = new HashMap<String, String>();
//        result.put("message", Constants.ROOM_NOT_FOUND);       // to do   Is this way ok or is it better handled with exceptions?

            return ResponseEntity.status(404).body(result);
        }

        return ResponseEntity.ok(new ReservationDtoOutput(deleted.get()));
    }

}
