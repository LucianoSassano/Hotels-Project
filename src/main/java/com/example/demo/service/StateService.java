package com.example.demo.service;

import com.example.demo.model.Country;
import com.example.demo.model.Estate;
import com.example.demo.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<Estate> listAllStates() {
        return stateRepository.findAll();
    }

    public Estate create(Estate state) {
        return stateRepository.save(state);
    }

    public Estate updateState(Estate state){
        Optional <Estate> stateDb = this.stateRepository.findById(state.getId());

        if (stateDb.isPresent()){
            Estate stateUpdate = stateDb.get();
            stateUpdate.setId(state.getId());
            stateUpdate.setName(state.getName());
            stateUpdate.setCities(state.getCities());
            stateUpdate.setCountry(state.getCountry());
            stateRepository.save(stateUpdate);
            return stateUpdate;
        }else{
            throw new RuntimeException();
        }
    }

    public Estate getById(Integer id) {
        return stateRepository.findById(id).get();
    }

    public void delete(Integer id){
        stateRepository.deleteById(id);
    }

}
