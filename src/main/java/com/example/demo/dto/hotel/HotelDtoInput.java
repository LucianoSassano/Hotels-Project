package com.example.demo.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDtoInput {

    private String name;

    private String address;

//    private Long cityId;

    private String email;

    private String phone;

    private Integer roomCapacity;

    private Integer rating;

}