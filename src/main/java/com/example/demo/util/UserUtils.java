package com.example.demo.util;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {

  public static List<UserDTO> listEntityToDTO(List<User> users) {
    return users.stream()
        .map(user -> UserDTO.generateInstanceFromUser(user))
        .collect(Collectors.toList());
  }
  }
