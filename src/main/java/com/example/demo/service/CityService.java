package com.example.demo.service;

import com.example.demo.dto.city.CityInputDto;
import com.example.demo.dto.city.CityOutputDto;
import com.example.demo.exception.DuplicateEntryException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.repository.CityRepository;
import com.example.demo.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

  @Autowired private final CityRepository cityRepository;

  public CityService(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @Transactional
  public City add(CityInputDto cityDto) {
    City cityToAdd = City.buildCityEntity(cityDto);

    cityRepository
        .findCityByZip(cityDto.getZipCode())
        .ifPresent(
            city -> {
              if (city.getIsDeleted()) {
                cityRepository.restoreCityById(city.getId());

              } else
                throw new DuplicateEntryException(
                    ErrorMessage.DUPLICATE_ENTRY + "zip_code : " + cityToAdd.getZip_code());
            });

    if (!cityRepository.findCityByZip(cityDto.getZipCode()).isPresent()) {
      cityRepository.save(cityToAdd);
    }

    return cityToAdd;
  }

  public List<CityOutputDto> listAllCities() {
    List<CityOutputDto> cityDtoList =
        cityRepository.findAll().stream().map(CityOutputDto::new).collect(Collectors.toList());
    if (cityDtoList.isEmpty()) {
      throw new NotFoundException(ErrorMessage.CITY_NOT_FOUND);
    }
    return cityDtoList;
  }

  public City updateCity(Long id, CityInputDto cityDto) {

    cityRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.CITY_NOT_FOUND));
    City updatedCity = City.buildCityEntity(cityDto);
    updatedCity.setId(cityDto.getId());
    cityRepository.save(updatedCity);
    return updatedCity;
  }

  public City getCity(Long id) {

    return cityRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.CITY_NOT_FOUND));
  }

  public City delete(Long id) {
    City cityToDelete =
        cityRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.CITY_NOT_FOUND));
    cityToDelete.setIsDeleted(true);
    cityRepository.save(cityToDelete);
    return cityToDelete;
  }
}
