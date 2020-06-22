package com.example.demo.controller;

import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.ReservationDtoOutput;
import com.example.demo.model.Reservation;
import com.example.demo.service.ReservationService;
import com.example.demo.util.ReservationUtils;
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
@RequestMapping(path = "/reservations")
@RestController
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity<ReservationDtoOutput> add(
      @Valid @RequestBody ReservationDtoInput reservationDtoInput) {
    return ResponseEntity.ok(new ReservationDtoOutput(reservationService.add(reservationDtoInput)));
  }

  @GetMapping
  public ResponseEntity<List<ReservationDtoOutput>> selectAll() {
    return ResponseEntity.ok(ReservationUtils.listEntityToDTO(reservationService.getAll()));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ReservationDtoOutput> selectById(@PathVariable Long id) {
    return ResponseEntity.ok(new ReservationDtoOutput(reservationService.getById(id)));
  }

  @GetMapping(path = "/hotel/{id}")
  public ResponseEntity<List<ReservationDtoOutput>> selectAllByHotelId(@PathVariable Long id) {
    return ResponseEntity.ok(
        ReservationUtils.listEntityToDTO(reservationService.getAllByHotelId(id)));
  }

  @GetMapping(path = "/room/{id}")
  public ResponseEntity<List<ReservationDtoOutput>> selectAllByRoomId(@PathVariable Long id) {
    return ResponseEntity.ok(
        ReservationUtils.listEntityToDTO(reservationService.getAllByRoomId(id)));
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<ReservationDtoOutput> replace(
      @PathVariable Long id, @Valid @RequestBody ReservationDtoInput reservationDtoInput) {
    return ResponseEntity.ok(
        new ReservationDtoOutput(reservationService.replace(id, reservationDtoInput)));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<ReservationDtoOutput> delete(@PathVariable Long id) {
    return ResponseEntity.ok(new ReservationDtoOutput(reservationService.delete(id)));
  }
}
