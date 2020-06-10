package com.example.demo.service;

import com.example.demo.dto.room.RoomDtoInput;
import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.model.Bedding;
import com.example.demo.model.Hotel;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final BeddingService beddingService;
    private final HotelService hotelService;


    public RoomDtoOutput add(RoomDtoInput roomDtoInput) {
        Room result = roomRepository.save(Room.buildRoomEntity(roomDtoInput));                                  //Create a Room based on the input DTO

        result.setBedding(beddingService.getById(roomDtoInput.getBeddingId()).orElse(new Bedding()));           //The DTO only provides the id of the bedding, to do the insert. This line adds also the bedding data to our Room obj
        result.setHotel(hotelService.getById(roomDtoInput.getHotelId()).orElse(new Hotel()));                    //The DTO only provides the id of the hotel, to do the insert. This line adds also the hotel data to our Room obj

        return new RoomDtoOutput(result);                                                                       // Converted to Output DTO and returned
    }

    public List<RoomDtoOutput> getAll() {
        List<RoomDtoOutput> roomDtoOutputList = roomRepository.findAll()
                .stream()
                .map((room) -> new RoomDtoOutput(room)).collect(Collectors.toList());

        return roomDtoOutputList;
    }

    public Optional<Room> getById(Long id) {

        return roomRepository.findById(id);

    }

    public List<RoomDtoOutput> getByHotelId(Long hotelId) {

        List<RoomDtoOutput> roomDtoOutputList = roomRepository.findAllByHotelId(hotelId)
                .stream()
                .map((room) -> new RoomDtoOutput(room)).collect(Collectors.toList());

        return roomDtoOutputList;
    }

    public Optional<Room> delete(Long id) {

        Optional<Room> roomToDelete = roomRepository.findById(id);           //Generate an optional with the room to delete

        if (roomToDelete.isPresent()) {                                        //If a room exists, we delete it and return it
            roomRepository.deleteById(id);

            return roomToDelete;
        }

        return roomToDelete;                                                   //Else, just return the empty optional to be handled on controller layer
    }

    public RoomDtoOutput replace(Long id, RoomDtoInput roomDtoInput) {

        Optional<Room> roomToUpdate = roomRepository.findById(id);

//        roomToUpdate.orElseThrow(()-> new NotFoundException)                    // To do. Is it ok to throw the exception on this layer ?

        Room updatedRoom = Room.buildRoomEntity(roomDtoInput);                    // Built entity and set the corresponding id before saving
        updatedRoom.setId(id);
        roomRepository.save(updatedRoom);

        updatedRoom.setBedding(beddingService.getById(roomDtoInput.getBeddingId()).orElse(new Bedding()));          //Adding bedding and hotel to the entity (before this line it only had bedding and hotel id)
        updatedRoom.setHotel(hotelService.getById(roomDtoInput.getHotelId()).orElse(new Hotel()));

        return new RoomDtoOutput(updatedRoom);
    }
}