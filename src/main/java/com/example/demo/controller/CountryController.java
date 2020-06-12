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
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.ok().body(countryService.listAllCountries());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity findCountryById(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.ok().body(countryService.getById(id));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado " + e);
        }
    }

    @PostMapping
    public HttpStatus createCountry(@RequestBody CountryDto country){
        countryService.create(country);
        return HttpStatus.OK;
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateCountry(@PathVariable Integer id , @RequestBody Country country) throws Exception {
        try {
            country.setId(id);
            return ResponseEntity.ok().body(this.countryService.updateCountry(country));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no econtrado " + e);
        }

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteCountry(@PathVariable Integer id){
        this.countryService.deleteCountryById(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }




}
