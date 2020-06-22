package com.example.demo.util;

import com.example.demo.dto.reservation.ReservationDtoOutput;
import com.example.demo.model.Reservation;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationUtils {

  public static List<ReservationDtoOutput> listEntityToDTO(List<Reservation> reservations) {
    return reservations.stream().map(ReservationDtoOutput::new).collect(Collectors.toList());
  }
}
