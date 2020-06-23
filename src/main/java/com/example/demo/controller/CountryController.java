package com.example.demo.controller;

import com.example.demo.dto.CountryDto;
import com.example.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/countries")
@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity getAllCountries() {

        return ResponseEntity.ok(countryService.listAllCountries());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity findCountryById(@PathVariable Long id) {

        return ResponseEntity.ok(new CountryDto(countryService.getById(id)));
    }

    @PostMapping
    public ResponseEntity createCountry(@RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.add(countryDto));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {

        return ResponseEntity.ok(countryService.updateCountry(id, countryDto));

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteCountry(@PathVariable Long id) {

        return ResponseEntity.ok(countryService.deleteCountryById(id));
    }


}
