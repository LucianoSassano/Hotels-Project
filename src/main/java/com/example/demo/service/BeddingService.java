package com.example.demo.service;

import com.example.demo.dto.bedding.BeddingDtoInput;
import com.example.demo.dto.bedding.UncheckedBedding;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Bedding;
import com.example.demo.repository.BeddingRepository;
import com.example.demo.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeddingService {

  private final BeddingRepository beddingRepository;

  public Bedding add(BeddingDtoInput beddingDtoInput) {
    return beddingRepository.save(Bedding.buildBeddingEntity(beddingDtoInput));
  }

  public List<Bedding> getAll() {

    return beddingRepository.findAll();
  }

  public Bedding getById(Long id) {
    return beddingRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.BEDDING_NOT_FOUND));
  }

  public Bedding delete(Long id) {

    Bedding beddingToDelete =
        beddingRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.BEDDING_NOT_FOUND));
    beddingRepository.deleteById(id);

    return beddingToDelete;
  }

  public Bedding replace(Long id, BeddingDtoInput beddingDtoInput) {

    beddingRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.BEDDING_NOT_FOUND));
    Bedding updatedBedding = Bedding.buildBeddingEntity(beddingDtoInput);
    updatedBedding.setId(id);

    return beddingRepository.save(updatedBedding);
  }

  public Bedding partialUpdate(Long id, UncheckedBedding uncheckedBedding) {
    Bedding bedding =
        beddingRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.BEDDING_NOT_FOUND));

    bedding.update(uncheckedBedding);

    return beddingRepository.save(bedding);
  }
}
