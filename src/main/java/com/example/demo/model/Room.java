package com.example.demo.model;

import com.example.demo.dto.room.RoomDtoInput;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bedding_id")
    private Bedding bedding;

    private Boolean status;

    private Double dailyRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public static Room buildRoomEntity(RoomDtoInput roomDtoInput) {

        return Room.builder()
                .category(roomDtoInput.getCategory())
                .bedding(Bedding.builder().id(roomDtoInput.getBeddingId()).build())
                .hotel(Hotel.builder().id(roomDtoInput.getHotelId()).build())
                .status(roomDtoInput.getStatus())
                .dailyRate(roomDtoInput.getDailyRate())
                .build();
    }
}
