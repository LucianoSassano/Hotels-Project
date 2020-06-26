package com.example.demo.dto;

import com.example.demo.model.CreditCard;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.util.UserExceptionMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

  @NotBlank(message = UserExceptionMessages.NOT_BLANK)
  @NotNull(message = UserExceptionMessages.NOT_NULL)
  private String name;

  @NotNull(message = UserExceptionMessages.NOT_NULL)
  @Positive
  private Integer dni;

  @NotBlank(message = UserExceptionMessages.NOT_BLANK)
  @NotNull(message = UserExceptionMessages.NOT_NULL)
  private String address;

  @NotBlank(message = UserExceptionMessages.NOT_BLANK)
  @NotNull(message = UserExceptionMessages.NOT_NULL)
  @Email
  private String email;

  @Positive private Long phone;

  @NotNull(message = UserExceptionMessages.NOT_NULL)
  private Role rol;

  public static UserDTO generateInstanceFromUser(User adn) {
    return UserDTO.builder()
        .address(adn.getAddress())
        .email(adn.getEmail())
        .dni(adn.getDni())
        .name(adn.getName())
        .phone(adn.getPhone())
        .rol(adn.getRol())
        .build();
  }

  private static Set<CardDTO> toCardsDTO(Set<CreditCard> listDtoCard) {
    return listDtoCard.stream()
        .map(card -> CardDTO.generateInstanceFromEntity(card))
        .collect(Collectors.toSet());
  }
}
