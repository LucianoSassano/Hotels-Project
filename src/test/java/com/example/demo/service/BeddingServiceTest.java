package com.example.demo.service;

import com.example.demo.dto.bedding.BeddingDtoInput;
import com.example.demo.dto.bedding.UncheckedBedding;
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
  void add_CompleteEntity_Ok() {

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

    BeddingDtoInput beddingDtoInput =
        BeddingDtoInput.builder().description("test description").maxCapacity(4).build();

    Assert.assertEquals(response, beddingService.add(beddingDtoInput));
  }

  @Test
  @DisplayName("getAll method working properly")
  void getAll_Empty_Ok() {

    List<Bedding> response = new ArrayList<>();

    when(mockBeddingRepository.findAll()).thenReturn(response);

    Assert.assertEquals(response, beddingService.getAll());
  }

  @Test
  @DisplayName("getById method working properly")
  void getById_ExistingId_Ok() {
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
  void getById_NonExistingId_NotFoundExceptionThrown() {

    when(mockBeddingRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> beddingService.getById(1L));
  }

  @Test
  @DisplayName("delete method working properly")
  void delete_ExistingId_Ok() {
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
  void delete_NonExistingId_NotFoundExceptionThrown() {

    when(mockBeddingRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(NotFoundException.class, () -> beddingService.delete(1L));
  }

  @Test
  @DisplayName("replace method working properly")
  void replace_ExistingId_Ok() {

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

    BeddingDtoInput beddingDtoInput =
        BeddingDtoInput.builder().description("test description").maxCapacity(4).build();

    when(mockBeddingRepository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(beddingBeforeUpdate));
    when(mockBeddingRepository.save(Mockito.any(Bedding.class))).thenReturn(response);

    Assertions.assertEquals(response, beddingService.replace(1L, beddingDtoInput));
  }

  @Test
  @DisplayName("replace method throwing notFoundException")
  void replace_NonExistingId_NotFoundExceptionThrown() {

    BeddingDtoInput beddingDtoInput =
        BeddingDtoInput.builder().description("test description").maxCapacity(4).build();

    when(mockBeddingRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        NotFoundException.class, () -> beddingService.replace(1L, beddingDtoInput));
  }

  @Test
  @DisplayName("partialUpdate method working properly")
  void partialUpdate_ExistingId_Ok() {

    List<Room> mockRooms = new ArrayList<>();

    Bedding response =
        Bedding.builder()
            .id(1L)
            .description("test description")
            .maxCapacity(4)
            .isDeleted(null)
            .rooms(mockRooms)
            .build();

    UncheckedBedding uncheckedBedding =
        UncheckedBedding.builder().description("new description testing").build();

    when(mockBeddingRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(response));

    when(mockBeddingRepository.save(Mockito.any(Bedding.class))).thenReturn(response);

    Assert.assertEquals(response, beddingService.partialUpdate(1L, uncheckedBedding));
  }

  @Test
  @DisplayName("partialUpdate method throwing notFoundException")
  void partialUpdate_NonExistingId_NotFoundExceptionThrown() {

    UncheckedBedding uncheckedBedding =
        UncheckedBedding.builder().description("new description testing").build();

    when(mockBeddingRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        NotFoundException.class, () -> beddingService.partialUpdate(1L, uncheckedBedding));
  }
}
