package com.example.demo.service;

import com.example.demo.dto.hotel.HotelDtoInput;
import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.dto.hotel.UncheckedHotel;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
import com.example.demo.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelDtoOutput add(HotelDtoInput hotelDtoInput) {
        return new HotelDtoOutput(hotelRepository.save(Hotel.buildHotelEntity(hotelDtoInput)));                                                                       // Converted to Output DTO and returned
    }

    public List<HotelDtoOutput> getAll() {
        List<HotelDtoOutput> hotelDtoOutputList = hotelRepository.findAll()
                .stream()
                .map((hotel) -> new HotelDtoOutput(hotel)).collect(Collectors.toList());

        if (hotelDtoOutputList.isEmpty())
            throw new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND);

        return hotelDtoOutputList;
    }

    public Hotel getById(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));
    }

    public Hotel delete(Long id) {

        Hotel hotelToDelete = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));
        hotelRepository.deleteById(id);

        return hotelToDelete;
    }

    public HotelDtoOutput replace(Long id, HotelDtoInput hotelDtoInput) {

        hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));
        Hotel updatedHotel = Hotel.buildHotelEntity(hotelDtoInput);
        updatedHotel.setId(id);

        return new HotelDtoOutput(hotelRepository.save(updatedHotel));
    }

    public HotelDtoOutput partialUpdate(Long id, UncheckedHotel uncheckedHotel) {
        Hotel hotelToUpdate = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));

        Optional.ofNullable(uncheckedHotel.getAddress()).ifPresent(hotelToUpdate::setAddress);
        Optional.ofNullable(uncheckedHotel.getEmail()).ifPresent(hotelToUpdate::setEmail);
        Optional.ofNullable(uncheckedHotel.getName()).ifPresent(hotelToUpdate::setName);
        Optional.ofNullable(uncheckedHotel.getPhone()).ifPresent(hotelToUpdate::setPhone);
        Optional.ofNullable(uncheckedHotel.getRating()).ifPresent(hotelToUpdate::setRating);
        Optional.ofNullable(uncheckedHotel.getRoomCapacity()).ifPresent(hotelToUpdate::setRoomCapacity);
//      Optional.ofNullable(uncheckedHotel.getCityId()).ifPresent(hotelToUpdate::setCityId);


        return new HotelDtoOutput(hotelRepository.save(hotelToUpdate));
    }

}
