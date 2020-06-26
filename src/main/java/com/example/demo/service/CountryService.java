package com.example.demo.service;

import com.example.demo.dto.CountryDto;
import com.example.demo.exception.notFoundException;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<CountryDto> listAllCountries() {
        List<CountryDto> countryDtoList = countryRepository.findAll()
                .stream()
                .map(CountryDto::new).collect(Collectors.toList());

        return countryDtoList;
    }

    public CountryDto add(CountryDto countryDto) {
        return new CountryDto(countryRepository.save(Country.buildCountryEntity(countryDto)));
    }

    public Country getById(Long id) {

        return countryRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.COUNTRY_NOT_FOUND));

    }

    public Country updateCountry(Long id, CountryDto countryDto) {
        countryRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.COUNTRY_NOT_FOUND));
        Country updatedCountry = Country.buildCountryEntity(countryDto);
        updatedCountry.setId(id);
        return countryRepository.save(updatedCountry);
    }

    public Country deleteCountryById(Long id) {
        Country countryToDelete = countryRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.COUNTRY_NOT_FOUND));
        countryRepository.deleteById(id);
        return countryToDelete;
    }
}
