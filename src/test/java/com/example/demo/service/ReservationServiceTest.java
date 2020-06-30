package com.example.demo.service;

import com.example.demo.dto.reservation.ReservationDtoInput;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Bedding;
import com.example.demo.model.Hotel;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
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

  @BeforeEach
  public void initialConfig() {
    reservationService = new ReservationService(mockReservationRepository, roomService);
  }

  @Test
  @DisplayName("add method working properly")
  void addTestOk() {

    Hotel mockHotel = new Hotel();
    Room mockRoom = new Room();

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
            .build();

    when(mockReservationRepository.save(Mockito.any(Reservation.class))).thenReturn(response);

    ReservationDtoInput reservationDtoInput =
        ReservationDtoInput.builder()
            .roomId(1L)
            .checkIn(null)
            .checkOut(null)
            .finalPrice(80D)
            .isPaid(false)
            .build();

    Assert.assertEquals(response, reservationService.add(reservationDtoInput));
  }

  @Test
  @DisplayName("getAll method working properly")
  void getAllTestOk() {

    List<Reservation> response = new ArrayList<>();

    when(mockReservationRepository.findAll()).thenReturn(response);

    Assert.assertEquals(response, reservationService.getAll());
  }

  @Test
  @DisplayName("getById method working properly")
  void getByIdTestOk() {

    Hotel mockHotel = new Hotel();
    Room mockRoom = new Room();

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
            .build();
    when(mockReservationRepository.findById(1L)).thenReturn(Optional.of(response));

    Assert.assertEquals(response, reservationService.getById(1L));
  }

  @Test
  @DisplayName("getById method throwing notFoundException")
  void getByIdTestNotFoundException() {

    when(mockReservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> reservationService.getById(1L));
  }

  @Test
  @DisplayName("delete method working properly")
  void deleteTestOk() {
    Hotel mockHotel = new Hotel();
    Room mockRoom = new Room();
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
            .build();

    when(mockReservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

    Assert.assertEquals(response, reservationService.delete(1L));
  }

  @Test
  @DisplayName("delete method throwing notFoundException")
  void deleteTestNotFoundException() {

    when(mockReservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> reservationService.delete(1L));
  }

  @Test
  @DisplayName("replace method working properly")
  void replaceTestOk() {

    Hotel mockHotel = new Hotel();
    Room mockRoom = new Room();

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
            .build();

    ReservationDtoInput reservationDtoInput =
        ReservationDtoInput.builder()
            .roomId(1L)
            .checkIn(null)
            .checkOut(null)
            .finalPrice(80D)
            .isPaid(false)
            .build();

    when(mockReservationRepository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(reservationBeforeUpdate));
    when(mockReservationRepository.save(Mockito.any(Reservation.class))).thenReturn(response);

    Assertions.assertEquals(response, reservationService.replace(1L, reservationDtoInput));
  }

  @Test
  @DisplayName("replace method throwing notFoundException")
  void replaceTestNotFoundException() {

    ReservationDtoInput reservationDtoInput =
        ReservationDtoInput.builder()
            .roomId(1L)
            .checkIn(null)
            .checkOut(null)
            .finalPrice(80D)
            .isPaid(false)
            .build();

    when(mockReservationRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        NotFoundException.class, () -> reservationService.replace(1L, reservationDtoInput));
  }
}

//  @Test
//  void getAllByHotelId() {}
//
//  @Test
//  void getAllByRoomId() {}
