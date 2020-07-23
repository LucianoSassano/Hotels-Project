package com.example.demo.controller;

import com.example.demo.dto.reservation.ReservationDtoCreated;
import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.ReservationDtoOutput;
import com.example.demo.dto.reservation.UncheckedReservation;
import com.example.demo.service.ReservationService;
import com.example.demo.util.ReservationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping(path = "/reservations")
@RestController
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity<ReservationDtoOutput> add(
      @Valid @RequestBody ReservationDtoInput reservationDtoInput) {
    return new ResponseEntity(
        new ReservationDtoCreated(reservationService.add(reservationDtoInput)), HttpStatus.CREATED);
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
  public ResponseEntity<ReservationDtoCreated> replace(
      @PathVariable Long id, @Valid @RequestBody ReservationDtoInput reservationDtoInput) {
    return ResponseEntity.ok(
        new ReservationDtoCreated(reservationService.replace(id, reservationDtoInput)));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<ReservationDtoCreated> delete(@PathVariable Long id) {
    return ResponseEntity.ok(new ReservationDtoCreated(reservationService.delete(id)));
  }

  @PatchMapping(path = "/{id}")
  public ResponseEntity<ReservationDtoCreated> partialUpdate(
      @PathVariable Long id, @Valid @RequestBody UncheckedReservation uncheckedReservation) {
    return ResponseEntity.ok(
        new ReservationDtoCreated(reservationService.partialUpdate(id, uncheckedReservation)));
  }
}
