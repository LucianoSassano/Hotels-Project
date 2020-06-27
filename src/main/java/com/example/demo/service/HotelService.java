package com.example.demo.service;

import com.example.demo.dto.hotel.HotelDtoInput;
import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Bedding;
import com.example.demo.model.City;
import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.repository.HotelRepository;
import com.example.demo.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

  private final HotelRepository hotelRepository;
  private final CityService cityService;

  public HotelDtoOutput add(HotelDtoInput hotelDtoInput) {

    Hotel hotel = Hotel.buildHotelEntity(hotelDtoInput);
    City city = cityService.getCity(hotelDtoInput.getCityId());

    hotel.setCity(city);

    return new HotelDtoOutput(hotelRepository.save(hotel));
  }

  public List<HotelDtoOutput> getAll() {

    return hotelRepository.findAll().stream().map(HotelDtoOutput::new).collect(Collectors.toList());
  }

  public Hotel getById(Long id) {
    return hotelRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));
  }

  public Hotel delete(Long id) {

    Hotel hotelToDelete =
        hotelRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));
    hotelRepository.deleteById(id);

    return hotelToDelete;
  }

  public HotelDtoOutput replace(Long id, HotelDtoInput hotelDtoInput) {

    hotelRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));

    Hotel updatedHotel = Hotel.buildHotelEntity(hotelDtoInput);

    City city = cityService.getCity(hotelDtoInput.getCityId());

    updatedHotel.setCity(city);
    updatedHotel.setId(id);

    return new HotelDtoOutput(hotelRepository.save(updatedHotel));
  }
}
