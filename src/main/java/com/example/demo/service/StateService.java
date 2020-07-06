package com.example.demo.service;

import com.example.demo.dto.state.EstateInputDto;
import com.example.demo.dto.state.EstateOutputDto;
import com.example.demo.exception.DuplicateEntryException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Estate;
import com.example.demo.repository.StateRepository;
import com.example.demo.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {

  @Autowired private StateRepository stateRepository;

  public List<EstateOutputDto> listAllStates() {
    return stateRepository.findAll().stream()
        .map(EstateOutputDto::new)
        .collect(Collectors.toList());
  }

  @Transactional
  public Estate add(EstateInputDto estateDto) {
    Estate stateToAdd = Estate.buildEstateEntity(estateDto);

    stateRepository
        .findStateById(estateDto.getId())
        .ifPresent(
            estate -> {
              if (estate.getIsDeleted()) {
                stateRepository.restoreStateById(estate.getId());
                stateToAdd.setIsDeleted(false);
                stateToAdd.setId(estateDto.getId());
              } else throw new DuplicateEntryException(ErrorMessage.DUPLICATE_ENTRY);
            });

    if (!stateRepository.findStateById(estateDto.getId()).isPresent()) {
      stateRepository.save(stateToAdd);
    }
    return stateToAdd;
  }

  public Estate updateState(Long id, EstateInputDto estateDto) {
    stateRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.STATE_NOT_FOUND));
    Estate updatedState = Estate.buildEstateEntity(estateDto);
    updatedState.setId(id);
    return stateRepository.save(updatedState);
  }

  public Estate getById(Long id) {
    return stateRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.STATE_NOT_FOUND));
  }

  public Estate delete(Long id) {
    Estate stateToDelete =
        stateRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.STATE_NOT_FOUND));
    stateToDelete.setIsDeleted(true);
    stateRepository.save(stateToDelete);

    return stateToDelete;
  }
}
