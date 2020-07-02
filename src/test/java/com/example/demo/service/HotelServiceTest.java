package com.example.demo.service;

import com.example.demo.dto.hotel.UncheckedHotel;
import com.example.demo.dto.hotel.HotelDtoInput;
import com.example.demo.exception.DuplicateEntryException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Hotel;
import com.example.demo.model.City;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.repository.HotelRepository;
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
class HotelServiceTest {

  private HotelService hotelService;

  @Mock HotelRepository mockHotelRepository;
  @Mock CityService mockCityService;

  @BeforeEach
  public void initialConfig() {
    hotelService = new HotelService(mockHotelRepository, mockCityService);
  }

  @Test
  @DisplayName("add method working properly")
  void add_CompleteEntity_Ok() {

    List<Reservation> mockReservations = new ArrayList<>();
    City mockCity = new City();
    List<Room> mockRooms = new ArrayList<>();

    Hotel response =
        Hotel.builder()
            .id(1L)
            .name("test name")
            .address("test address")
            .city(mockCity)
            .email("test@email.com")
            .phone("223123456")
            .roomCapacity(4)
            .rating(3L)
            .rooms(mockRooms)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    when(mockHotelRepository.save(Mockito.any(Hotel.class))).thenReturn(response);
    when(mockHotelRepository.findHotelByEmail(Mockito.anyString())).thenReturn(Optional.empty());
    HotelDtoInput hotelDtoInput =
        HotelDtoInput.builder()
            .name("test name")
            .address("test address")
            .cityId(1L)
            .email("test@email.com")
            .phone("223123456")
            .roomCapacity(4)
            .rating(3L)
            .build();

    Assert.assertEquals(response, hotelService.add(hotelDtoInput));
  }

  @Test
  @DisplayName("add method throwing DuplicateEntryException")
  void add_DuplicateUniqueField_DuplicateEntryExceptionThrown() {

    when(mockHotelRepository.findHotelByEmail(Mockito.anyString()))
        .thenReturn(Optional.of(Hotel.builder().isDeleted(false).build()));

    HotelDtoInput hotelDtoInput =
        HotelDtoInput.builder()
            .name("test name")
            .address("test address")
            .cityId(1L)
            .email("test@email.com")
            .phone("223123456")
            .roomCapacity(4)
            .rating(3L)
            .build();
    Assertions.assertThrows(DuplicateEntryException.class, () -> hotelService.add(hotelDtoInput));
  }

  @Test
  @DisplayName("getAll method working properly")
  void getAll_Empty_Ok() {

    List<Hotel> response = new ArrayList<>();

    when(mockHotelRepository.findAll()).thenReturn(response);

    Assert.assertEquals(response, hotelService.getAll());
  }

  @Test
  @DisplayName("getById method working properly")
  void getById_ExistingId_Ok() {
    List<Reservation> mockReservations = new ArrayList<>();
    City mockCity = new City();
    List<Room> mockRooms = new ArrayList<>();

    Hotel response =
        Hotel.builder()
            .id(1L)
            .name("test name")
            .address("test address")
            .city(mockCity)
            .email("test@email.com")
            .phone("223123456")
            .roomCapacity(4)
            .rating(3L)
            .rooms(mockRooms)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    when(mockHotelRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

    Assert.assertEquals(response, hotelService.getById(1L));
  }

  @Test
  @DisplayName("getById method throwing notFoundException")
  void getById_NonExistingId_NotFoundExceptionThrown() {

    when(mockHotelRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> hotelService.getById(1L));
  }

  @Test
  @DisplayName("delete method working properly")
  void delete_ExistingId_Ok() {
    List<Reservation> mockReservations = new ArrayList<>();
    City mockCity = new City();
    List<Room> mockRooms = new ArrayList<>();

    Hotel response =
        Hotel.builder()
            .id(1L)
            .name("test name")
            .address("test address")
            .city(mockCity)
            .email("test@email.com")
            .phone("223123456")
            .roomCapacity(4)
            .rating(3L)
            .rooms(mockRooms)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    when(mockHotelRepository.findById(1L)).thenReturn(Optional.of(response));

    Assert.assertEquals(response, hotelService.delete(1L));
  }

  @Test
  @DisplayName("delete method throwing notFoundException")
  void delete_NonExistingId_NotFoundExceptionThrown() {

    when(mockHotelRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> hotelService.delete(1L));
  }

  @Test
  @DisplayName("replace method working properly")
  void replace_ExistingId_Ok() {

    List<Reservation> mockReservations = new ArrayList<>();
    City mockCity = new City();
    List<Room> mockRooms = new ArrayList<>();

    Hotel hotelBeforeUpdate =
        Hotel.builder()
            .id(1L)
            .name("test name 2")
            .address("test address 2")
            .city(mockCity)
            .email("test@email2.com")
            .phone("2231234567")
            .roomCapacity(5)
            .rating(4L)
            .rooms(mockRooms)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    Hotel response =
        Hotel.builder()
            .id(1L)
            .name("test name")
            .address("test address")
            .city(mockCity)
            .email("test@email.com")
            .phone("223123456")
            .roomCapacity(4)
            .rating(3L)
            .rooms(mockRooms)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    HotelDtoInput hotelDtoInput =
        HotelDtoInput.builder()
            .name("test name")
            .address("test address")
            .cityId(1L)
            .email("test@email.com")
            .phone("223123456")
            .roomCapacity(4)
            .rating(3L)
            .build();

    when(mockHotelRepository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(hotelBeforeUpdate));
    when(mockHotelRepository.save(Mockito.any(Hotel.class))).thenReturn(response);
    when(mockCityService.getCity(Mockito.anyLong())).thenReturn(mockCity);

    Assertions.assertEquals(response, hotelService.replace(1L, hotelDtoInput));
  }

  @Test
  @DisplayName("replace method throwing notFoundException")
  void replace_NonExistingId_NotFoundExceptionThrown() {

    HotelDtoInput hotelDtoInput =
        HotelDtoInput.builder()
            .name("test name")
            .address("test address")
            .cityId(1L)
            .email("test@email.com")
            .phone("223123456")
            .roomCapacity(4)
            .rating(3L)
            .build();

    when(mockHotelRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> hotelService.replace(1L, hotelDtoInput));
  }

  @Test
  @DisplayName("partialUpdate method working properly")
  void partialUpdate_ExistingId_Ok() {

    UncheckedHotel uncheckedHotel = UncheckedHotel.builder().name("new name testing").build();

    List<Reservation> mockReservations = new ArrayList<>();
    City mockCity = new City();
    List<Room> mockRooms = new ArrayList<>();

    Hotel response =
        Hotel.builder()
            .id(1L)
            .name("test name")
            .address("test address")
            .city(mockCity)
            .email("test@email.com")
            .phone("223123456")
            .roomCapacity(4)
            .rating(3L)
            .rooms(mockRooms)
            .reservations(mockReservations)
            .isDeleted(false)
            .build();

    when(mockHotelRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

    when(mockHotelRepository.save(Mockito.any(Hotel.class))).thenReturn(response);

    Assert.assertEquals(response, hotelService.partialUpdate(1L, uncheckedHotel));
  }

  @Test
  @DisplayName("partialUpdate method throwing notFoundException")
  void partialUpdate_NonExistingId_NotFoundExceptionThrown() {

    UncheckedHotel uncheckedHotel = UncheckedHotel.builder().name("new name testing").build();

    when(mockHotelRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        NotFoundException.class, () -> hotelService.partialUpdate(1L, uncheckedHotel));
  }
}
