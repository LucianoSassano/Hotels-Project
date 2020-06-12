package com.example.demo.service;

import com.example.demo.dto.EstateDto;

import com.example.demo.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<EstateDto> listAllStates() {
        return stateRepository.findAll();
    }

    public EstateDto create(EstateDto state) {
        return stateRepository.save(state);
    }

    public EstateDto updateState(EstateDto state) throws Exception {
        Optional<EstateDto> stateDb = this.stateRepository.findById(state.getId());

        if (stateDb.isPresent()) {
            EstateDto stateUpdate = stateDb.get();
            stateUpdate.setId(state.getId());
            stateUpdate.setName(state.getName());
            stateUpdate.setCities(state.getCities());
            stateUpdate.setCountry(state.getCountry());
            stateRepository.save(stateUpdate);
            return stateUpdate;
        } else {
            throw new Exception();
        }
    }

    public EstateDto getById(Integer id) {
        return this.stateRepository.findById(id).get();
    }

    public void delete(Integer id) {
        stateRepository.deleteById(id);
    }

}
