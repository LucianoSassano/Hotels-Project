package com.example.demo.service;

import com.example.demo.dto.CardDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDtoInsert;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.CreditCard;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  private UserService userService;
  @Mock UserRepository mockUserRepository;
  @Mock CreditCardService mockCreditCardService;

  @BeforeEach
  public void config() {
    userService = new UserService(mockUserRepository, mockCreditCardService);
  }

  @Test
  @DisplayName("Test Correct Get All Users")
  void findAll_TestCorrectGetData() {
    List<User> result = new LinkedList<>();
    when(mockUserRepository.findAll()).thenReturn(result);
    Assert.assertEquals(result, userService.findAll());
  }

  @Test
  @DisplayName("Test Correct Insert User")
  void insert_HappyPathScenario() {
    List<CreditCard> mockCards = new LinkedList<>();
    User response =
        User.builder()
            .address("posadas 528")
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .createAt(null)
            .updateAt(null)
            .isDeleted(null)
            .id(Long.valueOf(1))
            .rol(Role.FINAL_CLIENT)
            .build();

    when(mockUserRepository.save(Mockito.any(User.class))).thenReturn(response);

    UserDtoInsert user =
        UserDtoInsert.builder()
            .address("posadas 528")
            .dni(31821923)
            .password("Password123")
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .rol(Role.FINAL_CLIENT)
            .build();
    Assert.assertEquals(response, userService.insert(user));
  }

  @Test
  @DisplayName("Test correct find by Id")
  void findById_Ok() {
    List<CreditCard> mockCards = new LinkedList<>();
    User user =
        User.builder()
            .address("posadas 528")
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .rol(Role.FINAL_CLIENT)
            .build();
    when(mockUserRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(user));
    Assert.assertEquals(user, this.userService.findById(Long.valueOf(1)));
  }

  @Test
  @DisplayName("Test throws Not Found Exception On Find By Id function")
  public void findById_ErrorNotFoundId() {
    assertThrows(NotFoundException.class, () -> userService.findById(Long.valueOf(5)));
  }

  @Test
  @DisplayName("Test Correct Update")
  void update_Ok() {
    List<CreditCard> mockCards = new LinkedList<>();
    UserDTO user =
        UserDTO.builder()
            .address("posadas 528")
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .rol(Role.FINAL_CLIENT)
            .build();
    User response =
        User.builder()
            .address("posadas 528")
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .createAt(null)
            .updateAt(null)
            .isDeleted(null)
            .id(Long.valueOf(1))
            .rol(Role.FINAL_CLIENT)
            .build();
    when(mockUserRepository.save(Mockito.any())).thenReturn(response);
    when(mockUserRepository.findByDni(31821923)).thenReturn(Optional.of(response));
    Assert.assertEquals(response, userService.update(user, 31821923));
  }

  @Test
  @DisplayName("Test Correct Find By Dni")
  void findByDni_Ok() {
    List<CreditCard> mockCards = new LinkedList<>();
    User user =
        User.builder()
            .address("posadas 528")
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .rol(Role.FINAL_CLIENT)
            .build();
    when(mockUserRepository.findByDni(Mockito.anyInt())).thenReturn(Optional.of(user));
    Assert.assertEquals(user, this.userService.findByDni(31821923));
  }

  @Test
  @DisplayName("Test throws Not Found Exception On Find By Dni function")
  public void findByDni_ErrorNotFoundId() {
    when(mockUserRepository.findByDni(Mockito.any())).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> userService.findByDni(31821923));
  }

  @Test
  void insertCard_CorrectInsertCard() {
    List<CreditCard> mockCards = new LinkedList<>();
    User user =
        User.builder()
            .address("posadas 528")
            .id(Long.valueOf(1))
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .rol(Role.FINAL_CLIENT)
            .build();
    CreditCard cardInserted =
        CreditCard.builder()
            .number(Long.valueOf(123456))
            .id(Long.valueOf(1))
            .createAt(null)
            .updateAt(null)
            .isDeleted(false)
            .userId(Long.valueOf(1))
            .build();
    CardDTO toInsert = CardDTO.builder().number(Long.valueOf(123456)).build();

    when(mockCreditCardService.insert(Mockito.any(CardDTO.class), Mockito.anyLong()))
        .thenReturn(cardInserted);

    when(mockUserRepository.findByDni(31821923)).thenReturn(Optional.of(user));

    Assert.assertEquals(cardInserted, userService.insertCard(toInsert, 31821923));
  }

  @Test
  @DisplayName("Test throw User Not Found Exception on Insert Card Method")
  void insertCard_FailTestTrhowUserNotFoundException() {
    CardDTO toInsert = CardDTO.builder().number(Long.valueOf(123456)).build();
    when(mockUserRepository.findByDni(Mockito.any())).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> userService.insertCard(toInsert, 31821923));
  }

  @Test
  @DisplayName("Test Correct Delete User Entity")
  void delete_CorrectDeleteEntityUser() {
    List<CreditCard> mockCards = new LinkedList<>();
    User user =
        User.builder()
            .address("posadas 528")
            .id(Long.valueOf(1))
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .rol(Role.FINAL_CLIENT)
            .build();
    when(mockUserRepository.findByDni(31821923)).thenReturn(Optional.of(user));
    Assert.assertEquals(user, userService.delete(31821923));
  }

  @Test
  @DisplayName("Test throw User Not Found Exception on delete Method")
  void delete_FailTestTrhowUserNotFoundException() {

    when(mockUserRepository.findByDni(Mockito.any())).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> userService.delete(31821923));
  }

  @Test
  @DisplayName("Test Correct Get User Data with their cards")
  void findUserWithCardsByDni_CorrectGetUserData() {
    List<CreditCard> mockCards = new LinkedList<>();
    CreditCard cardInserted =
        CreditCard.builder()
            .number(Long.valueOf(123456))
            .id(Long.valueOf(1))
            .createAt(null)
            .updateAt(null)
            .isDeleted(false)
            .userId(Long.valueOf(1))
            .build();
    mockCards.add(cardInserted);
    User user =
        User.builder()
            .address("posadas 528")
            .id(Long.valueOf(1))
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .rol(Role.FINAL_CLIENT)
            .build();

    when(mockUserRepository.findByDni(31821923)).thenReturn(Optional.of(user));
    Assert.assertEquals(user, userService.findUserWithCardsByDni(31821923));
  }

  @Test
  @DisplayName("Test throw User Not Found Exception on findUserWhitCards Method")
  void findUserWhitCards_FailTestTrhowUserNotFoundException() {

    when(mockUserRepository.findByDni(Mockito.any())).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> userService.findUserWithCardsByDni(31821923));
  }

  @Test
  @DisplayName("Test Correct User Update data")
  void update_CorrectUpdateTest() {
    List<CreditCard> mockCards = new LinkedList<>();
    User user =
        User.builder()
            .address("posadas 528")
            .id(Long.valueOf(1))
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .rol(Role.FINAL_CLIENT)
            .build();
    when(mockUserRepository.findByDni(31821923)).thenReturn(Optional.of(user));
    UserDTO dataToUpdate =
        UserDTO.builder()
            .address("posadas 528")
            .dni(31821923)
            .email("darioasaro@gmail.com")
            .name("Dario Asaro updated")
            .phone(Long.valueOf(223550))
            .rol(Role.FINAL_CLIENT)
            .build();
    User userUpdated =
        User.builder()
            .address("posadas 528")
            .id(Long.valueOf(1))
            .dni(31821923)
            .email("darioasaro@gmail.com")
            .name("Dario Asaro updated")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .rol(Role.FINAL_CLIENT)
            .build();
    when(mockUserRepository.save(Mockito.any(User.class))).thenReturn(userUpdated);

    Assert.assertEquals(userUpdated, userService.update(dataToUpdate, 31821923));
  }

  @Test
  @DisplayName("Test throw User Not Found Exception on findUserWhitCards Method")
  void update_FailTestTrhowUserNotFoundException() {
    UserDTO dataToUpdate =
        UserDTO.builder()
            .address("posadas 528")
            .dni(31821923)
            .email("darioasaro@gmail.com")
            .name("Dario Asaro updated")
            .phone(Long.valueOf(223550))
            .rol(Role.FINAL_CLIENT)
            .build();
    when(mockUserRepository.findByDni(Mockito.any())).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> userService.update(dataToUpdate, 31821923));
  }
}
