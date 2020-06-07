package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getAllCities(){
        return ResponseEntity.ok().body(this.cityService.listAllCities());
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Integer id){
        return ResponseEntity.ok().body(this.cityService.getCity(id));
    }

    @PostMapping("/cities")
    public ResponseEntity<City> createCity(@RequestBody City city){
        return ResponseEntity.ok().body(this.cityService.saveCity(city));
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Integer id , @RequestBody City city){
        return ResponseEntity.ok().body(this.cityService.updateCity(city));
    }
}
