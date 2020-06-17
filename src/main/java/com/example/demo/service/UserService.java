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
  @Autowired final UserRepository userRepository;
  @Autowired CreditCardService creditCardService;

  public List<UserDTO> findAll() {
    List<User> users = userRepository.findAll();
    return UserUtils.listEntityToDTO(users);
  }

  public UserDTO insert(UserDTO userNew) {
    User toSave = User.generateInstanceFromDTO(userNew);
    return UserDTO.generateInstanceFromUser(userRepository.save(toSave));
  }

  public UserDTO findById(Long id) {
    return userRepository
        .findById(id)
        .map(user -> UserDTO.generateInstanceFromUser(user))
        .orElseThrow(() -> new NotFoundException(UserExceptionMessages.USER_NOT_FOUND));
  }

  public UserDTO update(UserDTO userToUpdate, Integer dni) {
    return userRepository
        .findByDni(dni)
        .map(
            user -> {
              User updated = User.generateInstanceFromDTO(userToUpdate);
              updated.setId(user.getId());
              updated.setIsDeleted(false);
              userRepository.save(updated);
              return UserDTO.generateInstanceFromUser(updated);
            })
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
}
