package com.example.demo.service;

import com.example.demo.dto.CardDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.CreditCard;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.UserExceptionMessages;
import lombok.Data;
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

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User insert(UserDTO userNew) {
    User toSave = User.generateInstanceFromDTO(userNew);
    return userRepository.save(toSave);
  }

  public User findById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(UserExceptionMessages.USER_NOT_FOUND));
  }

  public User update(UserDTO dataForUpdate, Integer dni) {
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

  public CreditCard insertCard(CardDTO card, Integer dni) {
    return creditCardService.insert(card, findByDni(dni).getId());
  }

  public void delete(Integer dni) {
    userRepository.deleteById(findByDni(dni).getId());
  }

  public User findUserWithCardsByDni(Integer dni) {
    return findByDni(dni);
  }

  public User updateInstance(User user, UserDTO data) {
    User toUpdated = User.generateInstanceFromDTO(data);
    toUpdated.setId(user.getId());
    toUpdated.setIsDeleted(false);
    return userRepository.save(toUpdated);
  }
}
