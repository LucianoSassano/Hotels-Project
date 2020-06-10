package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.List;

@RequestMapping(path = "/countries")
@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok().body(countryService.listAllCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findCountryById(@PathVariable Integer id){
        return ResponseEntity.ok().body(countryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody Country country){
        return ResponseEntity.ok().body(countryService.create(country));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Integer id , @RequestBody Country country){
        country.setId(id);
        return ResponseEntity.ok().body(this.countryService.updateCountry(country));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCountry(@PathVariable Integer id){
        this.countryService.deleteCountryById(id);
        return HttpStatus.OK;
    }




}
