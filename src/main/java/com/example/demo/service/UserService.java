package com.example.demo.service;

import com.example.demo.dto.CardDTO;
import com.example.demo.dto.UserCardsDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.UserExceptionMessages;
import com.example.demo.util.UserUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Data
public class UserService {
  final UserRepository userRepository;
  final CreditCardService creditCardService;

  public UserService(UserRepository userRepository, CreditCardService creditCardService) {
    this.userRepository = userRepository;
    this.creditCardService = creditCardService;
  }

  public List<UserDTO> findAll() {
    List<User> users = userRepository.findAll();
    return UserUtils.listEntityToDTO(users);
  }

  public UserDTO insert(UserDTO userNew) {
    User toSave = User.generateInstanceFromDTO(userNew);
    return UserDTO.generateInstanceFromUser(userRepository.save(toSave));
  }

  public User findById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(UserExceptionMessages.USER_NOT_FOUND));
  }

  public UserDTO update(UserDTO dataForUpdate, Integer dni) {
    return userRepository
        .findByDni(dni)
        .map(user -> updateInstance(user, dataForUpdate))
        .orElseThrow(() -> new NotFoundException(UserExceptionMessages.USER_NOT_FOUND));
  }

  public User findByDni(Integer dni) {
    return userRepository
        .findByDni(dni)
        .orElseThrow(() -> new NotFoundException(UserExceptionMessages.USER_NOT_FOUND));
  }

  public CardDTO insertCard(CardDTO card, Integer dni) {
    return creditCardService.insert(card, findByDni(dni).getId());
  }

  public void delete(Integer dni) {
    userRepository.deleteById(findByDni(dni).getId());
  }

  public UserCardsDTO findCardsByDni(Integer dni) {
    User user = findByDni(dni);
    return UserCardsDTO.generateInstanceByUser(user);
  }

  public UserDTO updateInstance(User user, UserDTO data) {
    User toUpdated = User.generateInstanceFromDTO(data);
    toUpdated.setId(user.getId());
    toUpdated.setIsDeleted(false);
    userRepository.save(toUpdated);
    return UserDTO.generateInstanceFromUser(toUpdated);
  }
}
