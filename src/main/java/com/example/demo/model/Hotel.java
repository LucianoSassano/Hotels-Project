package com.example.demo.model;

import com.example.demo.dto.hotel.HotelDtoInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long id;

    private String name;

    private String address;

//    @ManyToOne
//    @JoinColumn(name = city_id)
//    private City city;

    private String email;

    private Long phone;

    private Boolean status;

    private Integer roomCapacity;

    private Integer rating;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Room> rooms;

    public static Hotel buildHotelEntity(HotelDtoInput hotelDtoInput) {
        return Hotel.builder()
                .name(hotelDtoInput.getName())
                .address(hotelDtoInput.getAddress())
//              .city(hotelDtoInput.getCityId())
                .email(hotelDtoInput.getEmail())
                .phone(hotelDtoInput.getPhone())
                .roomCapacity(hotelDtoInput.getRoomCapacity())
                .rating(hotelDtoInput.getRating())
                .status(true)
                .build();
    }

}
