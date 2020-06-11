package com.example.demo.dto;

import com.example.demo.model.CreditCard;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO implements Serializable {

    private Integer number;
    private Long user_id;

    public static CardDTO generateInstanceFromEntity(CreditCard card){
        return new CardDTO().builder()
                .user_id(card.getUser_id())
                .number(card.getNumber())
                .build();
    }
}
