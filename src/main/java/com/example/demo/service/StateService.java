package com.example.demo.service;

import com.example.demo.dto.state.EstateInputDto;
import com.example.demo.dto.state.EstateOutputDto;
import com.example.demo.exception.notFoundException;
import com.example.demo.model.Estate;
import com.example.demo.repository.StateRepository;
import com.example.demo.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public EstateOutputDto add(EstateInputDto estateDto) {

    return new EstateOutputDto((stateRepository.save(Estate.buildEstateEntity(estateDto))));
  }

  public EstateOutputDto updateState(Long id, EstateInputDto estateDto) {
    stateRepository
        .findById(id)
        .orElseThrow(() -> new notFoundException(ErrorMessage.STATE_NOT_FOUND));
    Estate updatedState = Estate.buildEstateEntity(estateDto);
    updatedState.setId(id);
    return new EstateOutputDto(stateRepository.save(updatedState));
  }

  public Estate getById(Long id) {
    return stateRepository
        .findById(id)
        .orElseThrow(() -> new notFoundException(ErrorMessage.STATE_NOT_FOUND));
  }

  public EstateOutputDto delete(Long id) {
    Estate stateToDelete =
        stateRepository
            .findById(id)
            .orElseThrow(() -> new notFoundException(ErrorMessage.STATE_NOT_FOUND));
    stateRepository.deleteById(id);
    return new EstateOutputDto(stateToDelete);
  }
}
