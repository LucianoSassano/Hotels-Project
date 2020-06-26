package com.example.demo.service;

import com.example.demo.dto.CityDto;
import com.example.demo.exception.DuplicateEntryException;
import com.example.demo.exception.notFoundException;
import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import com.example.demo.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public CityDto add(CityDto cityDto) {
        City cityToAdd = City.buildCityEntity(cityDto);
        cityRepository.findCityByZip(cityDto.getZipCode()).ifPresent(city -> {
            if (city.getIsDeleted()) {
                cityRepository.restoreCityById(city.getId());
                cityToAdd.setId(city.getId());
            } else
                throw new DuplicateEntryException(ErrorMessage.DUPLICATE_ENTRY + "zip_code : " + cityToAdd.getZip_code());
        });

        return new CityDto(cityRepository.save(cityToAdd));

    }

    public List<CityDto> listAllCities() {
        List<CityDto> cityDtoList = cityRepository.findAll()
                .stream()
                .map(CityDto::new).collect(Collectors.toList());
        if (cityDtoList.isEmpty()) {
            throw new notFoundException(ErrorMessage.CITY_NOT_FOUND);
        }
        return cityDtoList;
    }


    public City updateCity(Long id, CityDto cityDto) {

        cityRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.CITY_NOT_FOUND));
        City updatedCity = City.buildCityEntity(cityDto);
        updatedCity.setId(id);
        return cityRepository.save(updatedCity);

    }

    public City getCity(Long id) {

        return cityRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.CITY_NOT_FOUND));

    }

    public City delete(Long id) {
        City cityToDelete = cityRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.CITY_NOT_FOUND));
        cityRepository.deleteById(id);
        return cityToDelete;
    }
}
