package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.service.CityService;
import jdk.nashorn.internal.ir.IdentNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/cities")
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getAllCities(){
        return ResponseEntity.ok().body(this.cityService.listAllCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Integer id){
        return ResponseEntity.ok().body(this.cityService.getCity(id));
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city){
        return ResponseEntity.ok().body(this.cityService.saveCity(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Integer id , @RequestBody City city){
        return ResponseEntity.ok().body(this.cityService.updateCity(city));
    }
}