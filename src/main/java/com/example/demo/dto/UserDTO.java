package com.example.demo.dto;
import com.example.demo.model.CreditCard;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.smartcardio.Card;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Set<CardDTO> cards ;
    private String name;
    private String address;
    private String email;
    private Long phone;
    private Role rol;

    public static UserDTO generateInstanceFromUser(User adn){
        return UserDTO.builder().address(adn.getAddress())
                .cards(toCardsDTO(adn.getCards()))
                .email(adn.getEmail())
                .name(adn.getName())
                .phone(adn.getPhone())
                .rol(adn.getRol())
                .build();
    }

    private static Set<CardDTO> toCardsDTO (Set<CreditCard>listDtoCard){
        return  listDtoCard.stream()
                .map(card->CardDTO.generateInstanceFromEntity(card))
                .collect(Collectors.toSet());
    }



}
