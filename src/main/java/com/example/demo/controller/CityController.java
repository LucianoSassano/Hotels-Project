package com.example.demo.controller;

import com.example.demo.dto.CityDto;
import com.example.demo.service.CityService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/cities")
@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDto>> getAllCities() {
        return ResponseEntity.ok().body(this.cityService.listAllCities());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity getCityById(@PathVariable Integer id) throws Exception {
        try {
            return ResponseEntity.ok().body(this.cityService.getCity(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no econtrado " + e);
        }
    }

    @PostMapping
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto city) {
        return ResponseEntity.ok().body(this.cityService.saveCity(city));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity updateCity(@PathVariable Integer id, @RequestBody CityDto city) {
        try {
            return ResponseEntity.ok().body(this.cityService.updateCity(city));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado " + e);
        }

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteCity(@PathVariable Integer id) {
        this.cityService.delete(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
