package com.example.demo.util;

import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.model.Hotel;

import java.util.List;
import java.util.stream.Collectors;

public class HotelUtils {

  public static List<HotelDtoOutput> listEntityToDTO(List<Hotel> hotels) {
    return hotels.stream().map(HotelDtoOutput::new).collect(Collectors.toList());
  }
}
