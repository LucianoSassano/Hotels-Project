package com.example.demo.dto.reservation;

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
public class ReservationDtoCreated {

  private Long id;

  private Long roomId;

  private Long userId;

  private LocalDate checkIn;

  private LocalDate checkOut;

  private Double finalPrice;

  private Boolean isPaid;

  public ReservationDtoCreated(Reservation reservation) {
    this.id = reservation.getId();
    this.roomId = reservation.getRoom().getId();
    this.userId = reservation.getUser().getId();
    this.checkIn = reservation.getCheckIn();
    this.checkOut = reservation.getCheckOut();
    this.finalPrice = reservation.getFinalPrice();
    this.isPaid = reservation.getIsPaid();
  }
}
