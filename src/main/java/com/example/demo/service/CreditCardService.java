package com.example.demo.service;
import com.example.demo.dto.CardDTO;
import com.example.demo.model.CreditCard;
import com.example.demo.repository.CreditCardRepository;
import com.example.demo.util.CardsUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@Data
public class CreditCardService {
    @Autowired
    final CreditCardRepository creditCardRepository;

    public List<CardDTO> findAll(){
      return CardsUtils.convertToListDTO(creditCardRepository.findAll());
    }

    public CardDTO insert (CardDTO toInsert){
        CreditCard toSave = CreditCard.generateInstanceFromDTO(toInsert);
        toSave.setCreateAt(LocalDateTime.now());
        toSave.setDeleteAt(LocalDateTime.now());
        toSave.setUpdateAt(LocalDateTime.now());
        return CardDTO.generateInstanceFromEntity(creditCardRepository.save(toSave));
    }
    public CardDTO findById(Long id) throws Exception {
       Optional<CreditCard>optional =  creditCardRepository.findById(id.intValue());
        if(optional.isPresent()){
            return CardDTO.generateInstanceFromEntity(optional.get());
        }
        else{
            throw new Exception();
        }
    }



}
