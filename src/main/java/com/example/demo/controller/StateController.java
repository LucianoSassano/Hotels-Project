package com.example.demo.controller;

import com.example.demo.dto.EstateDto;
import com.example.demo.model.Estate;
import com.example.demo.service.StateService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/states")
@RestController
public class StateController {

    @Autowired
    private StateService stateService;


    @GetMapping
    public ResponseEntity<List<EstateDto>> getAllStates() {
        return ResponseEntity.ok().body(this.stateService.listAllStates());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getStateById(@PathVariable Integer id) throws Exception {
        try{
            return ResponseEntity.ok().body(this.stateService.getById(id));
        }catch (NotFoundException e){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no econtrado " + e);
        }
    }

    @PostMapping
    public ResponseEntity<EstateDto> createState(@RequestBody EstateDto state) {
        return ResponseEntity.ok().body(this.stateService.create(state));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateState(@PathVariable Integer id, @RequestBody EstateDto state) throws Exception {
        try {
            state.setId(id);
            return ResponseEntity.ok().body(this.stateService.updateState(state));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no econtrado " + e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteState(@PathVariable Integer id){
        this.stateService.delete(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }


}
