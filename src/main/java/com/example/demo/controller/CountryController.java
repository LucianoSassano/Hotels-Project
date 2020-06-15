package com.example.demo.controller;

import com.example.demo.dto.CountryDto;
import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping(path = "/countries")
@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity getAllCountries() throws Exception {
        try {
            return ResponseEntity.ok().body(countryService.listAllCountries());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found " + e);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity findCountryById(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.ok().body(countryService.getById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found " + e);
        }
    }

    @PostMapping
    public ResponseEntity createCountry(@RequestBody CountryDto country) throws Exception {
        try {
            countryService.create(country);
            return ResponseEntity.status(HttpStatus.OK).body(country);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Create not implemented " + e);

        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateCountry(@PathVariable Integer id, @RequestBody Country country) throws Exception {
        try {
            country.setId(id);
            return ResponseEntity.ok().body(this.countryService.updateCountry(country));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found " + e);
        }

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteCountry(@PathVariable Integer id) throws Exception {
        try {
            this.countryService.deleteCountryById(id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Delete not implemented " + e);
        }
    }


}
