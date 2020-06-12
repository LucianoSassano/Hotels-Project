package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> listAllCities() {
        return cityRepository.findAll();
    }

    public void save(City city) {
        cityRepository.save(city);
    }

    public City get(Integer id) {
        return cityRepository.findById(id).get();
    }

    public void delete(Integer id) {
        cityRepository.deleteById(id);
    }
}
