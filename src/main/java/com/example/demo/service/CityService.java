package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> listAllCities() {
        return cityRepository.findAll();
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(City city){
        Optional <City> cityDb = this.cityRepository.findById(city.getId());

        if (cityDb.isPresent()){
            City cityUpdate = cityDb.get();
            cityUpdate.setId(city.getId());
            cityUpdate.setName(city.getName());
            cityUpdate.setState(city.getState());
            cityUpdate.setZipCode(city.getZipCode());
            cityRepository.save(cityUpdate);
            return cityUpdate;
        }else{
            throw new RuntimeException();
        }
    }

    public City getCity(Integer id) {
        return cityRepository.findById(id).get();
    }

    public void delete(Integer id) {
        cityRepository.deleteById(id);
    }
}
