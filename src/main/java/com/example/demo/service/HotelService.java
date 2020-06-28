package com.example.demo.service;

import com.example.demo.dto.hotel.HotelDtoInput;
import com.example.demo.dto.hotel.UncheckedHotel;
import com.example.demo.exception.DuplicateEntryException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

  private final HotelRepository hotelRepository;

  @Transactional
  public Hotel add(HotelDtoInput hotelDtoInput) {
    Hotel hotelToAdd = Hotel.buildHotelEntity(hotelDtoInput);
    hotelRepository
        .findHotelByEmail(hotelDtoInput.getEmail())
        .ifPresent(
            hotel -> {
              if (hotel.getIsDeleted()) {
                hotelRepository.restoreHotelById(hotel.getId());
                hotelToAdd.setId(hotel.getId());
              } else
                throw new DuplicateEntryException(
                    ErrorMessage.DUPLICATE_ENTRY + "email: " + hotelToAdd.getEmail());
            });
    return hotelRepository.save(hotelToAdd);
  }

  public List<Hotel> getAll() {

    return hotelRepository.findAll();
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

  public Hotel replace(Long id, HotelDtoInput hotelDtoInput) {

    hotelRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));
    Hotel updatedHotel = Hotel.buildHotelEntity(hotelDtoInput);
    updatedHotel.setId(id);

    return hotelRepository.save(updatedHotel);
  }

  public Hotel partialUpdate(Long id, UncheckedHotel uncheckedHotel) {
    Hotel hotelToUpdate =
        hotelRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));

    Optional.ofNullable(uncheckedHotel.getAddress()).ifPresent(hotelToUpdate::setAddress);
    Optional.ofNullable(uncheckedHotel.getEmail()).ifPresent(hotelToUpdate::setEmail);
    Optional.ofNullable(uncheckedHotel.getName()).ifPresent(hotelToUpdate::setName);
    Optional.ofNullable(uncheckedHotel.getPhone()).ifPresent(hotelToUpdate::setPhone);
    Optional.ofNullable(uncheckedHotel.getRating()).ifPresent(hotelToUpdate::setRating);
    Optional.ofNullable(uncheckedHotel.getRoomCapacity()).ifPresent(hotelToUpdate::setRoomCapacity);
    //      Optional.ofNullable(uncheckedHotel.getCityId()).ifPresent(hotelToUpdate::setCityId);

    return hotelRepository.save(hotelToUpdate);
  }
}
