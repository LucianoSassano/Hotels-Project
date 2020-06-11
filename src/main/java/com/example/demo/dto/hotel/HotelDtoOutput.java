package com.example.demo.dto.hotel;

import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.model.Hotel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDtoOutput {

    private String name;

    private String address;

//    private CityDto cityDto;

    private String email;

    private String phone;

    private Integer roomCapacity;

    private Integer rating;

    @JsonBackReference
    private List<RoomDtoOutput> rooms;

    public HotelDtoOutput(Hotel hotel) {

        this.name = hotel.getName();
        this.address = hotel.getAddress();
//      this.cityDto = new cityDto(hotel.getCity());
        this.email = hotel.getEmail();
        this.phone = hotel.getPhone();
        this.roomCapacity = hotel.getRoomCapacity();
        this.rating = hotel.getRating();
    }


}
