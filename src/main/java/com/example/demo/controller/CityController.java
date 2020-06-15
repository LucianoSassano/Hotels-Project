package com.example.demo.controller;

import com.example.demo.dto.CityDto;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.NoSuchElementException;

@RequestMapping(path = "/cities")
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity getAllCities() throws Exception {
        try {
            return ResponseEntity.ok().body(this.cityService.listAllCities());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error " + e);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getCityById(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.ok().body(this.cityService.getCity(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found " + e);
        }
    }

    @PostMapping
    public ResponseEntity createCity(@RequestBody CityDto city) throws Exception {
        try {
            return ResponseEntity.ok().body(this.cityService.saveCity(city));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error " + e);
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateCity(@PathVariable Integer id, @RequestBody CityDto city) {
        try {
            return ResponseEntity.ok().body(this.cityService.updateCity(city));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found " + e);
        }

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteCity(@PathVariable Integer id) throws Exception {
        try {
            this.cityService.delete(id);
            return ResponseEntity.ok().body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Delete not implemented " + e);

        }

    }
}
