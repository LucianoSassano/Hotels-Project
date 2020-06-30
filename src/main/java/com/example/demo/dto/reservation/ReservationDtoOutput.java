package com.example.demo.dto.reservation;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.model.Reservation;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDtoOutput {

  private RoomDtoOutput room;

  private UserDTO user;

  private LocalDate checkIn;

  private LocalDate checkOut;

  private Double finalPrice;

  private Boolean isPaid;

  public ReservationDtoOutput(Reservation reservation) {
    this.room = new RoomDtoOutput(reservation.getRoom());
    this.user = UserDTO.generateInstanceFromUser(reservation.getUser());
    this.checkIn = reservation.getCheckIn();
    this.checkOut = reservation.getCheckOut();
    this.finalPrice = reservation.getFinalPrice();
    this.isPaid = reservation.getIsPaid();
  }
}
