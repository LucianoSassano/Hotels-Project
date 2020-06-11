package com.example.demo.dto.reservation;

import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Hotel hotel;
    private Room room;
    private Date checkIn;           //Pending to fix date format
    private Date checkOut;          //Pending to fix date format
    private Double finalPrice;
    private Boolean isPaid;

    public ReservationDto(Reservation reservation)
    {
        this.hotel=reservation.getHotel();
        this.room=reservation.getRoom();
        this.checkIn=reservation.getCheckIn();
        this.checkOut=reservation.getCheckOut();
        this.finalPrice=reservation.getFinalPrice();
        this.isPaid=reservation.getIsPaid();
    }
}
