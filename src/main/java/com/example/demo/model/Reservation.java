package com.example.demo.model;


import com.example.demo.dto.reservation.ReservationDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    //@ManyToOne
//@JoinColumn("user_id")
//    private User user;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")                //Pending to fix date format
    private Date checkIn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")                //Pending to fix date format

    private Date checkOut;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")                //Pending to fix date format

    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")                //Pending to fix date format

    private Date updatedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")                //Pending to fix date format

    private Date deletedAt;
    private Double finalPrice;
    private Boolean isPaid;


    public static Reservation buildReservationEntity(ReservationDto reservationDto) {
        return Reservation.builder()
                .hotel(reservationDto.getHotel())
                .room(reservationDto.getRoom())
                .checkIn(reservationDto.getCheckIn())
                .checkOut(reservationDto.getCheckOut())
                .finalPrice(reservationDto.getFinalPrice())
                .isPaid(reservationDto.getIsPaid())
                .build();
    }

}
