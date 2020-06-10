package com.example.demo.service;

import com.example.demo.dto.hotel.HotelDtoInput;
import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.model.Hotel;
import com.example.demo.repository.HotelRepository;
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
        Hotel result = hotelRepository.save(Hotel.buildHotelEntity(hotelDtoInput));                                  //Create and save an Hotel based on the input DTO

        return new HotelDtoOutput(result);                                                                       // Converted to Output DTO and returned
    }

    public List<HotelDtoOutput> getAll() {
        List<HotelDtoOutput> hotelDtoOutputList = hotelRepository.findAll()
                .stream()
                .map((hotel) -> new HotelDtoOutput(hotel)).collect(Collectors.toList());

        return hotelDtoOutputList;
    }

    public Optional<Hotel> getById(Long id) {

        return hotelRepository.findById(id);

    }

    public Optional<Hotel> delete(Long id) {

        Optional<Hotel> hotelToDelete = hotelRepository.findById(id);           //Generate an optional with the hotel to delete

        if (hotelToDelete.isPresent()) {                                        //If a hotel exists, we delete it and return it
            hotelRepository.deleteById(id);

            return hotelToDelete;
        }

        return hotelToDelete;                                                   //Else, just return the empty optional to be handled on controller layer
    }

    public HotelDtoOutput replace(Long id, HotelDtoInput hotelDtoInput) {

        Optional<Hotel> hotelToUpdate = hotelRepository.findById(id);           //Generate an optional with the hotel to update

//        hotelToUpdate.orElseThrow(()-> new NotFoundException)                 // To do. Is it ok to throw the exception on this layer ?

        Hotel updatedHotel = Hotel.buildHotelEntity(hotelDtoInput);             // Built entity and set the corresponding id before saving
        updatedHotel.setId(id);
        hotelRepository.save(updatedHotel);

        return new HotelDtoOutput(updatedHotel);

    }
}