package com.example.demo.model;


import com.example.demo.dto.reservation.ReservationDtoInput;

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
                .hotel(Hotel.builder().id(reservationDtoInput.getHotelId()).build())
                .room(Room.builder().id(reservationDtoInput.getRoomId()).build())
                .checkIn(LocalDate.parse(reservationDtoInput.getCheckIn(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))          //To do  Function in SharedUtils to validate the format
                .checkOut(LocalDate.parse(reservationDtoInput.getCheckOut(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))        // To do Function in SharedUtils to validate the format
                .finalPrice(reservationDtoInput.getFinalPrice())
                .isPaid(reservationDtoInput.getIsPaid())
                .build();
    }

}
