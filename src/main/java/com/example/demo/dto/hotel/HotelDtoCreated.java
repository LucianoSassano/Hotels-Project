package com.example.demo.dto.hotel;

import com.example.demo.dto.CityDto;
import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.model.City;
import com.example.demo.model.Hotel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDtoCreated {

  private Long id;

  private String name;

  private String address;

  private Long cityId;

  private String email;

  private String phone;

  private Integer roomCapacity;

  private Long rating;

  public HotelDtoCreated(Hotel hotel) {
    this.id = hotel.getId();
    this.name = hotel.getName();
    this.address = hotel.getAddress();
    this.cityId = hotel.getCity().getId();
    this.email = hotel.getEmail();
    this.phone = hotel.getPhone();
    this.roomCapacity = hotel.getRoomCapacity();
    this.rating = hotel.getRating();
  }
}
