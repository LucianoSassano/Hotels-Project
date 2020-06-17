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
        List<CountryDto> cityDtoList = countryRepository.findAll()
                .stream()
                .map(CountryDto::new).collect(Collectors.toList());
        if (cityDtoList.isEmpty()) {
            throw new notFoundException(ErrorMessage.COUNTRY_NOT_FOUND);
        }
        return cityDtoList;
    }

    public CountryDto add(CountryDto countryDto) {
        return new CountryDto(countryRepository.save(Country.buildCountryEntity(countryDto)));
    }

    public Country getById(Integer id) {

        return countryRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.COUNTRY_NOT_FOUND));

    }

    public Country updateCountry(Integer id, CountryDto countryDto) {
        countryRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.COUNTRY_NOT_FOUND));
        Country updatedCountry = Country.buildCountryEntity(countryDto);
        updatedCountry.setId(id);
        return countryRepository.save(updatedCountry);
    }

    public Country deleteCountryById(Integer id) {
        Country countryToDelete = countryRepository.findById(id).orElseThrow(() -> new notFoundException(ErrorMessage.COUNTRY_NOT_FOUND));
        countryRepository.deleteById(id);
        return countryToDelete;
    }
}
