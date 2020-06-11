package com.example.demo.dto.reservation;

import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDtoInput {

    private Long roomId;
    private String checkIn;
    private String checkOut;
    private Double finalPrice;
    private Boolean isPaid;

}
