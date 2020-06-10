package com.example.demo.controller;

import com.example.demo.model.Estate;
import com.example.demo.service.StateService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/states")
@RestController
public class StateController {

    @Autowired
    private StateService stateService;


    @GetMapping
    public ResponseEntity<List<Estate>> getAllStates(){
        return ResponseEntity.ok().body(this.stateService.listAllStates());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estate> getStateById(@PathVariable Integer id){
        return ResponseEntity.ok().body(this.stateService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Estate> createState(@RequestBody Estate state){
        return ResponseEntity.ok().body(this.stateService.create(state));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estate> updateState(@PathVariable Integer id,@RequestBody Estate state){
        state.setId(id);
        return ResponseEntity.ok().body(this.stateService.updateState(state));
    }
}
