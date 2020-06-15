package com.example.demo.controller;

import com.example.demo.dto.EstateDto;
import com.example.demo.service.StateService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping(path = "/states")
@RestController
public class StateController {

    @Autowired
    private StateService stateService;


    @GetMapping
    public ResponseEntity getAllStates() throws Exception {
        try {
            return ResponseEntity.ok().body(this.stateService.listAllStates());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found" + e);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getStateById(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.ok().body(this.stateService.getById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found " + e);
        }
    }

    @PostMapping
    public ResponseEntity createState(@RequestBody EstateDto state) throws Exception {
        try {
            return ResponseEntity.ok().body(this.stateService.create(state));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Creation not implemented " + e);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateState(@PathVariable Integer id, @RequestBody EstateDto state) throws Exception {
        try {
            state.setId(id);
            return ResponseEntity.ok().body(this.stateService.updateState(state));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found " + e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteState(@PathVariable Integer id) throws Exception {
        try {
            this.stateService.delete(id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Delete not implemented " + e);
        }
    }


}
