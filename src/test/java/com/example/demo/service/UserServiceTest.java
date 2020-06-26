package com.example.demo.service;

import com.example.demo.dto.CardDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.CreditCard;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.Assert;
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
  @Mock
  UserRepository mockUserRepository;
  @Mock
  CreditCardService mockCreditCardService;
  @BeforeEach
  public void config(){
    userService = new UserService(mockUserRepository,mockCreditCardService);
  }

  @Test
  @DisplayName("Test Correct Find All")
  void findAll() {
    List<User>result = new LinkedList<>();
    when(mockUserRepository.findAll()).thenReturn(result);
    Assert.assertEquals(result,userService.findAll());

  }

  @Test
  @DisplayName("Test Correct Insert User")
  void insert_HappyPathScenario() {
    List<CreditCard>mockCards = new LinkedList<>();
    User response  = User.builder().address("posadas 528")
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .createAt(null)
            .updateAt(null)
            .isDeleted(null)
            .id(Long.valueOf(1))
            .rol(Role.FINAL_CLIENT).build();


    when(mockUserRepository.save(Mockito.any(User.class)))
            .thenReturn(response);

    UserDTO user = UserDTO.builder().address("posadas 528")
                    .dni(31821923)
                    .email("darioasaro29@gmail.com")
                    .name("Dario Asaro")
                    .phone(Long.valueOf(223550))
                    .rol(Role.FINAL_CLIENT).build();
    Assert.assertEquals(response,userService.insert(user));
  }

  @Test
  @DisplayName("Test correct find by Id")
  void findById_Ok() {
    List<CreditCard>mockCards = new LinkedList<>();
    User user  = User.builder().address("posadas 528")
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .rol(Role.FINAL_CLIENT).build();
    when(mockUserRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(user));
    Assert.assertEquals(user,this.userService.findById(Long.valueOf(1)));
  }
  @Test
  @DisplayName("Test throws Not Found Exception On Find By Id function")
 public void findById_ErrorNotFoundId(){
   // when(mockUserRepository.findById(Mockito.any())).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class,()->userService.findById(Long.valueOf(5)));
  }


  @Test
  @DisplayName("Test Correct Update")
  void update_Ok() {
    List<CreditCard>mockCards = new LinkedList<>();
    UserDTO user  = UserDTO.builder().address("posadas 528")
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .rol(Role.FINAL_CLIENT).build();
    User response  = User.builder().address("posadas 528")
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .createAt(null)
            .updateAt(null)
            .isDeleted(null)
            .id(Long.valueOf(1))
            .rol(Role.FINAL_CLIENT).build();
      when(mockUserRepository.save(Mockito.any())).thenReturn(response);
      when(mockUserRepository.findByDni(31821923)).thenReturn(Optional.of(response));
      Assert.assertEquals(response,userService.update(user,31821923));
  }

  @Test
  @DisplayName("Test Correct Find By Dni")
  void findByDni_Ok() {
    List<CreditCard>mockCards = new LinkedList<>();
    User user  = User.builder().address("posadas 528")
            .dni(31821923)
            .email("darioasaro29@gmail.com")
            .name("Dario Asaro")
            .phone(Long.valueOf(223550))
            .cards(mockCards)
            .rol(Role.FINAL_CLIENT).build();
    when(mockUserRepository.findByDni(Mockito.anyInt())).thenReturn(Optional.of(user));
    Assert.assertEquals(user,this.userService.findByDni(31821923));
  }

  @Test
  @DisplayName("Test throws Not Found Exception On Find By Dni function")
  public void findByDni_ErrorNotFoundId(){
     when(mockUserRepository.findByDni(Mockito.any())).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class,()->userService.findByDni(31821923));
  }

  @Test
  void insertCard() {
      when(mockCreditCardService.insert(Mockito.any(CardDTO.class),Mockito.anyLong()));

  }

  @Test
  void delete() {}

  @Test
  void findUserWithCardsByDni() {}

  @Test
  void updateInstance() {}
}