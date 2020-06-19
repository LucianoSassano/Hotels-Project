package com.example.demo.service;

import com.example.demo.dto.hotel.HotelDtoInput;
import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

  private final HotelRepository hotelRepository;

  @Transactional
  public HotelDtoOutput add(HotelDtoInput hotelDtoInput) {
    Hotel hotelToAdd = Hotel.buildHotelEntity(hotelDtoInput);
    hotelRepository
        .findHotelByEmail(hotelDtoInput.getEmail())
        .ifPresent(
            hotel -> {
              if (hotel.getIsDeleted()) {
                hotelRepository.restoreHotelById(hotel.getId());
                hotelToAdd.setId(hotel.getId());
              }
            });
    return new HotelDtoOutput(hotelRepository.save(hotelToAdd));
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
    updatedHotel.setId(id);

    return new HotelDtoOutput(hotelRepository.save(updatedHotel));
  }
}
