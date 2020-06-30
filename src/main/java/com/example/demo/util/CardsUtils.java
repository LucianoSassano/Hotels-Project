package com.example.demo.util;

import com.example.demo.dto.CardDTO;
import com.example.demo.model.CreditCard;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CardsUtils {

  public static Set<CreditCard> setCardsDTOtoEntity(Set<CardDTO> listDtoCard) {
    return listDtoCard.stream()
        .map(card -> CreditCard.generateInstanceFromDTO(card))
        .collect(Collectors.toSet());
  }

  public static Set<CardDTO> setCardEntityToDTO(Set<CreditCard> listDtoCard) {
    return listDtoCard.stream()
        .map(card -> CardDTO.generateInstanceFromEntity(card))
        .collect(Collectors.toSet());
  }

  public static List<CardDTO> convertToListDTO(List<CreditCard> list) {
    return list.stream()
        .map(card -> CardDTO.generateInstanceFromEntity(card))
        .collect(Collectors.toList());
  }
}
