package com.example.demo.dto;

import com.example.demo.model.CreditCard;
import com.example.demo.model.User;
import com.example.demo.util.CardsUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCardsDTO implements Serializable {
    private List<CardDTO> cards;
    private String name;
    private Integer dni;

    public static UserCardsDTO generateInstanceByUser(User adn){
        return new UserCardsDTO().builder()
                .cards(CardsUtils.convertToListDTO(adn.getCards()))
                .name(adn.getName())
                .dni(adn.getDni())
                .build();
    }
}
