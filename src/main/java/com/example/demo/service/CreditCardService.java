package com.example.demo.service;

import com.example.demo.dto.CardDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.CreditCard;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.util.CardsUtils;
import com.example.demo.util.UserExceptionMessages;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Data
public class CreditCardService {
  @Autowired final CreditCardRepository creditCardRepository;

  public List<CardDTO> findAll() {
    return CardsUtils.convertToListDTO(creditCardRepository.findAll());
  }

  public CreditCard insert(CardDTO toInsert, Long id) {
    CreditCard toSave = CreditCard.generateInstanceFromDTO(toInsert);
    toSave.setUserId(id);
    return creditCardRepository.save(toSave);
  }

  public CardDTO findById(Long id) {
    return creditCardRepository
        .findById(id)
        .map(card -> CardDTO.generateInstanceFromEntity(card))
        .orElseThrow(() -> new NotFoundException(UserExceptionMessages.CARD_NOT_FOUND));
  }

  public CreditCard findByNumber(Long number) {
    return creditCardRepository
        .findByNumber(number)
        .orElseThrow(() -> new NotFoundException(UserExceptionMessages.CARD_NOT_FOUND));
  }

  public void delete(Long number) {
    creditCardRepository.deleteById(findByNumber(number).getId());
  }
}
