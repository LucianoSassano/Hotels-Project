package com.example.demo.service;

import com.example.demo.dto.CityDto;
import com.example.demo.exception.notFoundException;
import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.util.ErrorMessage;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CityDto> listAllCities() {
        List<CityDto> cityDtoList = cityRepository.findAll()
                .stream()
                .map(CityDto::new).collect(Collectors.toList());
        if (cityDtoList.isEmpty()) {
            throw new notFoundException(ErrorMessage.CITY_NOT_FOUND);
        }
        return cityDtoList;
    }

    public CityDto add(CityDto cityDto) {
        return new CityDto(cityRepository.save(City.buildCityEntity(cityDto)));
    }

    public City updateCity(Integer id, CityDto cityDto) {

        cityRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.CITY_NOT_FOUND));
        City updatedCity = City.buildCityEntity(cityDto);
        updatedCity.setId(id);
        return cityRepository.save(updatedCity);

    }

    public City getCity(Integer id) {

        return cityRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.CITY_NOT_FOUND));

    }

    public City delete(Integer id) {
        City cityToDelete = cityRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.CITY_NOT_FOUND));
        cityRepository.deleteById(id);
        return cityToDelete;
    }
}
