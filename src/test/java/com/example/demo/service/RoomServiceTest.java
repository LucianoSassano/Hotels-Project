package com.example.demo.service;

import com.example.demo.dto.bedding.UncheckedBedding;
import com.example.demo.dto.room.RoomDtoInput;
import com.example.demo.dto.room.UncheckedRoom;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Bedding;
import com.example.demo.model.Category;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

  private RoomService roomService;

  @Mock RoomRepository mockRoomRepository;
  @Mock BeddingService beddingService;
  @Mock HotelService hotelService;

  @BeforeEach
  public void initialConfig() {
    roomService = new RoomService(mockRoomRepository, beddingService, hotelService);
  }

  @Test
  @DisplayName("add method working properly")
  void add_CompleteEntity_Ok() {

    List<Reservation> mockReservations = new ArrayList<>();
    Hotel mockHotel = new Hotel();
    Bedding mockBedding = new Bedding();

    Room response =
        Room.builder()
            .id(1L)
            .category(Category.SUITE)
            .bedding(mockBedding)
            .hotel(mockHotel)
            .status(true)
            .dailyRate(21D)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();
    when(mockRoomRepository.save(Mockito.any(Room.class))).thenReturn(response);

    RoomDtoInput roomDtoInput =
        RoomDtoInput.builder()
            .category(Category.SUITE)
            .beddingId(1L)
            .hotelId(1L)
            .status(true)
            .dailyRate(21D)
            .build();
    RoomDtoInput.builder()
        .category(Category.SUITE)
        .beddingId(1L)
        .hotelId(1L)
        .status(true)
        .dailyRate(21D)
        .build();

    Assert.assertEquals(response, roomService.add(roomDtoInput));
  }

  @Test
  @DisplayName("getAll method working properly")
  void getAll_Empty_Ok() {

    List<Room> response = new ArrayList<>();

    when(mockRoomRepository.findAll()).thenReturn(response);

    Assert.assertEquals(response, roomService.getAll());
  }

  @Test
  @DisplayName("getById method working properly")
  void getById_ExistingId_Ok() {
    List<Reservation> mockReservations = new ArrayList<>();
    Hotel mockHotel = new Hotel();
    Bedding mockBedding = new Bedding();

    Room response =
        Room.builder()
            .id(1L)
            .category(Category.SUITE)
            .bedding(mockBedding)
            .hotel(mockHotel)
            .status(true)
            .dailyRate(21D)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    when(mockRoomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

    Assert.assertEquals(response, roomService.getById(1L));
  }

  @Test
  @DisplayName("getById method throwing notFoundException")
  void getById_NonExistingId_NotFoundExceptionThrown() {

    when(mockRoomRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> roomService.getById(1L));
  }

  @Test
  @DisplayName("delete method working properly")
  void delete_ExistingId_Ok() {
    List<Reservation> mockReservations = new ArrayList<>();
    Hotel mockHotel = new Hotel();
    Bedding mockBedding = new Bedding();

    Room response =
        Room.builder()
            .id(1L)
            .category(Category.SUITE)
            .bedding(mockBedding)
            .hotel(mockHotel)
            .status(true)
            .dailyRate(21D)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    when(mockRoomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

    Assert.assertEquals(response, roomService.delete(1L));
  }

  @Test
  @DisplayName("delete method throwing notFoundException")
  void delete_NonExistingId_NotFoundExceptionThrown() {

    when(mockRoomRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> roomService.delete(1L));
  }

  @Test
  @DisplayName("replace method working properly")
  void replace_ExistingId_Ok() {

    List<Reservation> mockReservations = new ArrayList<>();
    Hotel mockHotel = new Hotel();
    Bedding mockBedding = new Bedding();

    Room roomBeforeUpdate =
        Room.builder()
            .id(1L)
            .category(Category.ECONOMY)
            .bedding(mockBedding)
            .hotel(mockHotel)
            .status(true)
            .dailyRate(18D)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    Room response =
        Room.builder()
            .id(1L)
            .category(Category.SUITE)
            .bedding(mockBedding)
            .hotel(mockHotel)
            .status(true)
            .dailyRate(21D)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    RoomDtoInput roomDtoInput =
        RoomDtoInput.builder()
            .category(Category.SUITE)
            .beddingId(1L)
            .hotelId(1L)
            .status(true)
            .dailyRate(21D)
            .build();

    when(mockRoomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(roomBeforeUpdate));
    when(mockRoomRepository.save(Mockito.any(Room.class))).thenReturn(response);

    Assertions.assertEquals(response, roomService.replace(1L, roomDtoInput));
  }

  @Test
  @DisplayName("replace method throwing notFoundException")
  void replace_NonExistingId_NotFoundExceptionThrown() {

    RoomDtoInput roomDtoInput =
        RoomDtoInput.builder()
            .category(Category.SUITE)
            .beddingId(1L)
            .hotelId(1L)
            .status(true)
            .dailyRate(21D)
            .build();

    when(mockRoomRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> roomService.replace(1L, roomDtoInput));
  }

  @Test
  @DisplayName("getAllByHotelId method working properly")
  void getAllByHotelId_Empty_Ok() {

    List<Room> response = new ArrayList<>();

    when(mockRoomRepository.findAllByHotelId(Mockito.anyLong())).thenReturn(response);

    Assert.assertEquals(response, roomService.getAllByHotelId(1L));
  }

  @Test
  @DisplayName("partialUpdate method working properly")
  void partialUpdate_ExistingId_Ok() {

    List<Reservation> mockReservations = new ArrayList<>();
    Hotel mockHotel = new Hotel();
    Bedding mockBedding = new Bedding();

    Room response =
        Room.builder()
            .id(1L)
            .category(Category.SUITE)
            .bedding(mockBedding)
            .hotel(mockHotel)
            .status(true)
            .dailyRate(21D)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    UncheckedRoom uncheckedRoom = UncheckedRoom.builder().dailyRate(99D).build();

    when(mockRoomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

    when(mockRoomRepository.save(Mockito.any(Room.class))).thenReturn(response);

    Assert.assertEquals(response, roomService.partialUpdate(1L, uncheckedRoom));
  }

  @Test
  @DisplayName("partialUpdate method throwing notFoundException")
  void partialUpdate_NonExistingId_NotFoundExceptionThrown() {

    UncheckedRoom uncheckedRoom = UncheckedRoom.builder().dailyRate(99D).build();

    when(mockRoomRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        NotFoundException.class, () -> roomService.partialUpdate(1L, uncheckedRoom));
  }
}
