package com.example.demo.service;

import com.example.demo.dto.room.RoomDtoInput;
import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.dto.room.UncheckedRoom;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.util.ErrorMessage;
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

        Room result = Room.buildRoomEntity(roomDtoInput);                                  //Create a Room based on the input DTO

        result.setBedding(beddingService.getById(roomDtoInput.getBeddingId()));         //The DTO only provides the id of the bedding, to do the insert. This line adds also the bedding data to our Room obj
        result.setHotel(hotelService.getById(roomDtoInput.getHotelId()));               //The DTO only provides the id of the hotel, to do the insert. This line adds also the hotel data to our Room obj

        return new RoomDtoOutput(roomRepository.save(result));                                                                       // Converted to Output DTO and returned
    }

    public List<RoomDtoOutput> getAll() {

        List<RoomDtoOutput> roomDtoOutputList = roomRepository.findAll()
                .stream()
                .map((room) -> new RoomDtoOutput(room)).collect(Collectors.toList());

        if (roomDtoOutputList.isEmpty())
            throw new NotFoundException(ErrorMessage.ROOM_NOT_FOUND);

        return roomDtoOutputList;
    }

    public Room getById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.ROOM_NOT_FOUND));
    }

    public List<RoomDtoOutput> getByHotelId(Long hotelId) {

        List<RoomDtoOutput> rooms = roomRepository.findAllByHotelId(hotelId)
                .stream()
                .map((room) -> new RoomDtoOutput(room)).collect(Collectors.toList());

        if (rooms.isEmpty())
            throw new NotFoundException(ErrorMessage.ROOM_NOT_FOUND);

        return rooms;
    }

    public Room delete(Long id) {

        Room roomToDelete = roomRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));
        roomRepository.deleteById(id);

        return roomToDelete;
    }

    public RoomDtoOutput replace(Long id, RoomDtoInput roomDtoInput) {

        roomRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.ROOM_NOT_FOUND));
        Room updatedRoom = Room.buildRoomEntity(roomDtoInput);
        updatedRoom.setId(id);

        updatedRoom.setBedding(beddingService.getById(roomDtoInput.getBeddingId()));        //Adding bedding and hotel to the entity (before this line it only had bedding and hotel id)
        updatedRoom.setHotel(hotelService.getById(roomDtoInput.getHotelId()));

        return new RoomDtoOutput(roomRepository.save(updatedRoom));
    }

    public RoomDtoOutput partialUpdate(Long id, UncheckedRoom uncheckedRoom) {
        Room roomToUpdate = roomRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.ROOM_NOT_FOUND));

        Optional.ofNullable(uncheckedRoom.getBeddingId()).ifPresent(beddingId -> roomToUpdate.setBedding(beddingService.getById(beddingId)));
        Optional.ofNullable(uncheckedRoom.getHotelId()).ifPresent(hotelId -> roomToUpdate.setHotel(hotelService.getById(hotelId)));
        Optional.ofNullable(uncheckedRoom.getCategory()).ifPresent(roomToUpdate::setCategory);
        Optional.ofNullable(uncheckedRoom.getDailyRate()).ifPresent(roomToUpdate::setDailyRate);
        Optional.ofNullable(uncheckedRoom.getStatus()).ifPresent(roomToUpdate::setStatus);

        return new RoomDtoOutput(roomRepository.save(roomToUpdate));
    }

}
