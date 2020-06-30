package com.example.demo.service;

import com.example.demo.dto.country.CountryInputDto;
import com.example.demo.dto.country.CountryOutputDto;
import com.example.demo.exception.notFoundException;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.util.ErrorMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

  final CountryRepository countryRepository;

  public CountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  public List<CountryOutputDto> listAllCountries() {

    return countryRepository.findAll().stream()
        .map(CountryOutputDto::new)
        .collect(Collectors.toList());
  }

  public CountryOutputDto add(CountryInputDto countryDto) {
    Country country = Country.buildCountryEntity(countryDto);

    return new CountryOutputDto((countryRepository.save(country)));
  }

  public Country getById(Long id) {

    return countryRepository
        .findById(id)
        .orElseThrow(() -> new notFoundException(ErrorMessage.COUNTRY_NOT_FOUND));
  }

  public CountryOutputDto updateCountry(Long id, CountryInputDto countryDto) {
    countryRepository
        .findById(id)
        .orElseThrow(() -> new notFoundException(ErrorMessage.COUNTRY_NOT_FOUND));
    Country updatedCountry = Country.buildCountryEntity(countryDto);
    updatedCountry.setId(id);
    return new CountryOutputDto(countryRepository.save(updatedCountry));
  }

  public Country deleteCountryById(Long id) {
    Country countryToDelete =
        countryRepository
            .findById(id)
            .orElseThrow(() -> new notFoundException(ErrorMessage.COUNTRY_NOT_FOUND));
    countryRepository.deleteById(id);
    return countryToDelete;
  }
}
