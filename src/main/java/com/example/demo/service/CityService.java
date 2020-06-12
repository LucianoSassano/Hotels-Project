package com.example.demo.service;

import com.example.demo.dto.CityDto;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CityDto> listAllCities() {
        return cityRepository.findAll();
    }

    public CityDto saveCity(CityDto city) {
        return cityRepository.save(city);
    }

    public CityDto updateCity(CityDto city) throws Exception {
        Optional<CityDto> cityDb = this.cityRepository.findById(city.getId());

        if (cityDb.isPresent()) {
            CityDto cityUpdate = cityDb.get();
            cityUpdate.setId(city.getId());
            cityUpdate.setName(city.getName());
            cityUpdate.setState(city.getState());
            cityUpdate.setZipCode(city.getZipCode());
            cityRepository.save(cityUpdate);
            return cityUpdate;
        } else {
            throw new Exception();
        }
    }

    public CityDto getCity(Integer id) {

        return cityRepository.findById(id).get();


    }

    public void delete(Integer id) {
        cityRepository.deleteById(id);
    }
}
