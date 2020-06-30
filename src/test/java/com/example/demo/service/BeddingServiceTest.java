package com.example.demo.service;

import com.example.demo.dto.bedding.BeddingDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Bedding;
import com.example.demo.model.Room;
import com.example.demo.repository.BeddingRepository;
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
class BeddingServiceTest {

  private BeddingService beddingService;

  @Mock BeddingRepository mockBeddingRepository;

  @BeforeEach
  public void initialConfig() {
    beddingService = new BeddingService(mockBeddingRepository);
  }

  @Test
  @DisplayName("add method working properly")
  void addTestOk() {

    List<Room> mockRooms = new ArrayList<>();

    Bedding response =
        Bedding.builder()
            .id(1L)
            .description("test description")
            .maxCapacity(4)
            .isDeleted(null)
            .rooms(mockRooms)
            .build();

    when(mockBeddingRepository.save(Mockito.any(Bedding.class))).thenReturn(response);

    BeddingDto beddingDtoInput =
        BeddingDto.builder().description("test description").maxCapacity(4).build();

    Assert.assertEquals(response, beddingService.add(beddingDtoInput));
  }

  @Test
  @DisplayName("getAll method working properly")
  void getAllTestOk() {

    List<Bedding> response = new ArrayList<>();

    when(mockBeddingRepository.findAll()).thenReturn(response);

    Assert.assertEquals(response, beddingService.getAll());
  }

  @Test
  @DisplayName("getById method working properly")
  void getByIdTestOk() {
    List<Room> mockRooms = new ArrayList<>();
    Bedding response =
        Bedding.builder()
            .id(1L)
            .description("test description")
            .maxCapacity(4)
            .isDeleted(null)
            .rooms(mockRooms)
            .build();
    when(mockBeddingRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

    Assert.assertEquals(response, beddingService.getById(1L));
  }

  @Test
  @DisplayName("getById method throwing notFoundException")
  void getByIdTestNotFoundException() {

    when(mockBeddingRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> beddingService.getById(1L));
  }

  @Test
  @DisplayName("delete method working properly")
  void deleteTestOk() {
    List<Room> mockRooms = new ArrayList<>();
    Bedding response =
        Bedding.builder()
            .id(1L)
            .description("test description")
            .maxCapacity(4)
            .isDeleted(null)
            .rooms(mockRooms)
            .build();

    when(mockBeddingRepository.findById(1L)).thenReturn(Optional.of(response));

    Assert.assertEquals(response, beddingService.delete(1L));
  }

  @Test
  @DisplayName("delete method throwing notFoundException")
  void deleteTestNotFoundException() {

    when(mockBeddingRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> beddingService.delete(1L));
  }

  @Test
  @DisplayName("replace method working properly")
  void replaceTestOk() {

    List<Room> mockRooms = new ArrayList<>();

    Bedding beddingBeforeUpdate =
        Bedding.builder()
            .id(1L)
            .description("test description before update")
            .maxCapacity(99)
            .isDeleted(null)
            .rooms(mockRooms)
            .build();

    Bedding response =
        Bedding.builder()
            .id(1L)
            .description("test description")
            .maxCapacity(4)
            .isDeleted(null)
            .rooms(mockRooms)
            .build();

    BeddingDto beddingDtoInput =
        BeddingDto.builder().description("test description").maxCapacity(4).build();

    when(mockBeddingRepository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(beddingBeforeUpdate));
    when(mockBeddingRepository.save(Mockito.any(Bedding.class))).thenReturn(response);

    Assertions.assertEquals(response, beddingService.replace(1L, beddingDtoInput));
  }

  @Test
  @DisplayName("replace method throwing notFoundException")
  void replaceTestNotFoundException() {

    BeddingDto beddingDtoInput =
        BeddingDto.builder().description("test description").maxCapacity(4).build();

    when(mockBeddingRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        NotFoundException.class, () -> beddingService.replace(1L, beddingDtoInput));
  }
}
