package com.example.demo.service;

import com.example.demo.model.Estate;
import com.example.demo.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<Estate> listAllStates() {
        return stateRepository.findAll();
    }

    public void save(Estate state) {
        stateRepository.save(state);
    }

    public Estate get(Integer id) {
        return stateRepository.findById(id).get();
    }

    public void delete(Integer id){
        stateRepository.deleteById(id);
    }

}
