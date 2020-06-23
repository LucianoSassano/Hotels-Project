package com.example.demo.controller;

import com.example.demo.dto.CityDto;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/cities")
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity getAllCities() {
        return ResponseEntity.ok(cityService.listAllCities());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getCityById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getCity(id));
    }

    @PostMapping
    public ResponseEntity createCity(@RequestBody CityDto cityDto) {

        return ResponseEntity.ok().body(cityService.add(cityDto));

    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateCity(@PathVariable Long id, @RequestBody CityDto city) {

        return ResponseEntity.ok().body(cityService.updateCity(id, city));

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteCity(@PathVariable Long id) {

        return ResponseEntity.ok().body(cityService.delete(id));

    }
}
