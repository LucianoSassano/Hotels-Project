package com.example.demo.service;

import com.example.demo.dto.EstateDto;
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

    @Autowired
    private StateRepository stateRepository;

    public List<Estate> listAllStates() {
        return stateRepository.findAll();

    }

    public EstateDto add(EstateDto estateDto) {

        return new EstateDto(stateRepository.save(Estate.buildEstateEntity(estateDto)));

    }

    public Estate updateState(Long id, EstateDto estateDto) {
        stateRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.STATE_NOT_FOUND));
        Estate updatedState = Estate.buildEstateEntity(estateDto);
        updatedState.setId(id);
        return stateRepository.save(updatedState);
    }

    public Estate getById(Long id) {
        return stateRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.STATE_NOT_FOUND));
    }

    public Estate delete(Long id) {
        Estate stateToDelete = stateRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.STATE_NOT_FOUND));
        stateRepository.deleteById(id);
        return stateToDelete;
    }

}
