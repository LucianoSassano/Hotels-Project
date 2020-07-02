package com.example.demo.service;

import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.dto.reservation.UncheckedReservation;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.repository.ReservationRepository;
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
class ReservationServiceTest {

  private ReservationService reservationService;

  @Mock ReservationRepository mockReservationRepository;
  @Mock RoomService roomService;
  @Mock UserService userService;

  @BeforeEach
  public void initialConfig() {
    reservationService =
        new ReservationService(mockReservationRepository, roomService, userService);
  }

  @Test
  @DisplayName("add method working properly")
  void add_CompleteEntity_Ok() {
    User mockUser = new User();
    Hotel mockHotel = new Hotel();
    Room mockRoom = Room.builder().hotel(mockHotel).build();

    Reservation response =
        Reservation.builder()
            .id(1L)
            .room(mockRoom)
            .hotel(mockHotel)
            .checkIn(null)
            .checkOut(null)
            .createdAt(null)
            .updatedAt(null)
            .finalPrice(80D)
            .isPaid(false)
            .isDeleted(false)
            .user(mockUser)
            .build();

    when(mockReservationRepository.save(Mockito.any(Reservation.class))).thenReturn(response);
    when(roomService.getById(Mockito.anyLong())).thenReturn(mockRoom);
    when(userService.findById(Mockito.anyLong())).thenReturn(mockUser);

    ReservationDtoInput reservationDtoInput =
        ReservationDtoInput.builder()
            .roomId(1L)
            .checkIn("2020/06/05")
            .checkOut("2020/06/15")
            .finalPrice(80D)
            .isPaid(false)
            .userId(1L)
            .build();

    Assert.assertEquals(response, reservationService.add(reservationDtoInput));
  }

  @Test
  @DisplayName("getAll method working properly")
  void getAll_Empty_Ok() {

    List<Reservation> response = new ArrayList<>();

    when(mockReservationRepository.findAll()).thenReturn(response);
    Assert.assertEquals(response, reservationService.getAll());
  }

  @Test
  @DisplayName("getById method working properly")
  void getById_ExistingId_Ok() {

    User mockUser = new User();
    Hotel mockHotel = new Hotel();
    Room mockRoom = Room.builder().hotel(mockHotel).build();

    Reservation response =
        Reservation.builder()
            .id(1L)
            .room(mockRoom)
            .hotel(mockHotel)
            .checkIn(null)
            .checkOut(null)
            .createdAt(null)
            .updatedAt(null)
            .finalPrice(80D)
            .isPaid(false)
            .isDeleted(false)
            .user(mockUser)
            .build();
    when(mockReservationRepository.findById(1L)).thenReturn(Optional.of(response));

    Assert.assertEquals(response, reservationService.getById(1L));
  }

  @Test
  @DisplayName("getById method throwing notFoundException")
  void getById_NonExistingId_NotFoundExceptionThrown() {

    when(mockReservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> reservationService.getById(1L));
  }

  @Test
  @DisplayName("delete method working properly")
  void delete_ExistingId_Ok() {
    User mockUser = new User();
    Hotel mockHotel = new Hotel();
    Room mockRoom = Room.builder().hotel(mockHotel).build();
    Reservation response =
        Reservation.builder()
            .id(1L)
            .room(mockRoom)
            .hotel(mockHotel)
            .checkIn(null)
            .checkOut(null)
            .createdAt(null)
            .updatedAt(null)
            .finalPrice(80D)
            .isPaid(false)
            .isDeleted(false)
            .user(mockUser)
            .build();

    when(mockReservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

    Assert.assertEquals(response, reservationService.delete(1L));
  }

  @Test
  @DisplayName("delete method throwing notFoundException")
  void delete_NonExistingId_NotFoundExceptionThrown() {

    when(mockReservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> reservationService.delete(1L));
  }

  @Test
  @DisplayName("replace method working properly")
  void replace_ExistingId_Ok() {

    User mockUser = new User();
    Hotel mockHotel = new Hotel();
    Room mockRoom = Room.builder().hotel(mockHotel).build();

    Reservation reservationBeforeUpdate =
        Reservation.builder()
            .id(1L)
            .room(mockRoom)
            .hotel(mockHotel)
            .checkIn(null)
            .checkOut(null)
            .createdAt(null)
            .updatedAt(null)
            .finalPrice(70D)
            .isPaid(true)
            .isDeleted(false)
            .user(mockUser)
            .build();

    Reservation response =
        Reservation.builder()
            .id(1L)
            .room(mockRoom)
            .hotel(mockHotel)
            .checkIn(null)
            .checkOut(null)
            .createdAt(null)
            .updatedAt(null)
            .finalPrice(80D)
            .isPaid(false)
            .isDeleted(false)
            .user(mockUser)
            .build();

    ReservationDtoInput reservationDtoInput =
        ReservationDtoInput.builder()
            .roomId(1L)
            .checkIn("2020/06/15")
            .checkOut("2020/06/05")
            .finalPrice(80D)
            .isPaid(false)
            .userId(1L)
            .build();

    when(mockReservationRepository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(reservationBeforeUpdate));
    when(roomService.getById(Mockito.anyLong())).thenReturn(mockRoom);
    when(userService.findById(Mockito.anyLong())).thenReturn(mockUser);
    when(mockReservationRepository.save(Mockito.any(Reservation.class))).thenReturn(response);

    Assertions.assertEquals(response, reservationService.replace(1L, reservationDtoInput));
  }

  @Test
  @DisplayName("replace method throwing notFoundException")
  void replace_NonExistingId_NotFoundExceptionThrown() {

    ReservationDtoInput reservationDtoInput =
        ReservationDtoInput.builder()
            .roomId(1L)
            .checkIn(null)
            .checkOut(null)
            .finalPrice(80D)
            .isPaid(false)
            .userId(1L)
            .build();

    when(mockReservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        NotFoundException.class, () -> reservationService.replace(1L, reservationDtoInput));
  }

  @Test
  @DisplayName("getAllByHotelId method working properly")
  void getAllByHotelId_Empty_Ok() {
    List<Reservation> response = new ArrayList<>();

    when(mockReservationRepository.findAllByHotelId(Mockito.anyLong())).thenReturn(response);

    Assert.assertEquals(response, reservationService.getAllByHotelId(1L));
  }

  @Test
  @DisplayName("getAllByRoomId method working properly")
  void getAllByRoomId_Empty_Ok() {
    List<Reservation> response = new ArrayList<>();

    when(mockReservationRepository.findAllByRoomId(Mockito.anyLong())).thenReturn(response);

    Assert.assertEquals(response, reservationService.getAllByRoomId(1L));
  }

  @Test
  @DisplayName("partialUpdate method working properly")
  void partialUpdate_ExistingId_Ok() {

    User mockUser = new User();
    Hotel mockHotel = new Hotel();
    Room mockRoom = Room.builder().hotel(mockHotel).build();

    Reservation response =
        Reservation.builder()
            .id(1L)
            .room(mockRoom)
            .hotel(mockHotel)
            .checkIn(null)
            .checkOut(null)
            .createdAt(null)
            .updatedAt(null)
            .finalPrice(80D)
            .isPaid(false)
            .isDeleted(false)
            .user(mockUser)
            .build();

    UncheckedReservation uncheckedReservation =
        UncheckedReservation.builder().finalPrice(90D).build();

    when(mockReservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

    when(mockReservationRepository.save(Mockito.any(Reservation.class))).thenReturn(response);

    Assert.assertEquals(response, reservationService.partialUpdate(1L, uncheckedReservation));
  }

  @Test
  @DisplayName("partialUpdate method throwing notFoundException")
  void partialUpdate_NonExistingId_NotFoundExceptionThrown() {

    UncheckedReservation uncheckedReservation =
        UncheckedReservation.builder().finalPrice(80D).build();

    when(mockReservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        NotFoundException.class, () -> reservationService.partialUpdate(1L, uncheckedReservation));
  }
}
