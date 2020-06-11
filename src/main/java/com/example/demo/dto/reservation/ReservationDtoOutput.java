package com.example.demo.dto.reservation;

import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.model.Reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDtoOutput {

    private RoomDtoOutput room;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private Double finalPrice;

    private Boolean isPaid;


    public ReservationDtoOutput(Reservation reservation) {
        this.room = new RoomDtoOutput(reservation.getRoom());
        this.checkIn = reservation.getCheckIn();
        this.checkOut = reservation.getCheckOut();
        this.finalPrice = reservation.getFinalPrice();
        this.isPaid = reservation.getIsPaid();
    }
}
