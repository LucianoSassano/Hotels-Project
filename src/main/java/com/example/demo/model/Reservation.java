package com.example.demo.model;


import com.example.demo.dto.reservation.ReservationDtoInput;

import com.example.demo.util.Constants;
import com.example.demo.util.SharedUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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

    private LocalDate checkIn;

    private LocalDate checkOut;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Double finalPrice;

    private Boolean isPaid;


    public static Reservation buildReservationEntity(ReservationDtoInput reservationDtoInput) {
        return Reservation.builder()
                .room(Room.builder().id(reservationDtoInput.getRoomId()).build())
                .checkIn(LocalDate.parse(SharedUtils.adjustLocalDate(reservationDtoInput.getCheckIn()), DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)))
                .checkOut(LocalDate.parse(SharedUtils.adjustLocalDate(reservationDtoInput.getCheckOut()), DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)))
                .finalPrice(reservationDtoInput.getFinalPrice())
                .isPaid(reservationDtoInput.getIsPaid())
                .build();
    }

}
