package com.example.demo.service;

import com.example.demo.dto.room.RoomDtoInput;
import com.example.demo.dto.room.UncheckedRoom;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;
  private final BeddingService beddingService;
  private final HotelService hotelService;

  public void checkExistenceOfAdjacentObjects(Long beddingId, Long hotelId) {
    beddingService.getById(beddingId);
    hotelService.getById(hotelId);
  }

  public Room add(RoomDtoInput roomDtoInput) {

    checkExistenceOfAdjacentObjects(roomDtoInput.getBeddingId(), roomDtoInput.getHotelId());

    return roomRepository.save(Room.buildRoomEntity(roomDtoInput));
  }

  public List<Room> getAll() {

    return roomRepository.findAll();
  }

  public Room getById(Long id) {

    return roomRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.ROOM_NOT_FOUND));
  }

  public List<Room> getAllByHotelId(Long hotelId) {

    return roomRepository.findAllByHotelId(hotelId);
  }

  public Room delete(Long id) {

    Room roomToDelete =
        roomRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.HOTEL_NOT_FOUND));
    roomRepository.deleteById(id);

    return roomToDelete;
  }

  public Room replace(Long id, RoomDtoInput roomDtoInput) {

    roomRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.ROOM_NOT_FOUND));

    checkExistenceOfAdjacentObjects(roomDtoInput.getBeddingId(), roomDtoInput.getHotelId());

    Room updatedRoom = Room.buildRoomEntity(roomDtoInput);
    updatedRoom.setId(id);

    return roomRepository.save(updatedRoom);
  }

  public Room partialUpdate(Long id, UncheckedRoom uncheckedRoom) {
    Room roomToUpdate =
        roomRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.ROOM_NOT_FOUND));

    roomToUpdate.update(uncheckedRoom);

    Optional.ofNullable(uncheckedRoom.getBeddingId())
        .ifPresent(beddingId -> roomToUpdate.setBedding(beddingService.getById(beddingId)));
    Optional.ofNullable(uncheckedRoom.getHotelId())
        .ifPresent(hotelId -> roomToUpdate.setHotel(hotelService.getById(hotelId)));

    return roomRepository.save(roomToUpdate);
  }
}
