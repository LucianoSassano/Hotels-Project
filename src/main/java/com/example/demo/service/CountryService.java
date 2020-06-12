package com.example.demo.service;


import com.example.demo.dto.CountryDto;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<CountryDto> listAllCountries() {
        return countryRepository.findAll();
    }

    public CountryDto create(CountryDto country) {
        return countryRepository.save(country);
    }

    public CountryDto getById(Integer id) {

        return this.countryRepository.findById(id).get();

    }

    public CountryDto updateCountry(Country country) throws Exception {
        Optional<CountryDto> countryDb = this.countryRepository.findById(country.getId());

        if (countryDb.isPresent()) {
            CountryDto countryUpdate = countryDb.get();
            countryUpdate.setId(country.getId());
            countryUpdate.setName(country.getName());
            countryUpdate.setEstates(country.getEstates());
            countryRepository.save(countryUpdate);
            return countryUpdate;
        } else {
            throw new Exception();
        }
    }

    public void deleteCountryById(Integer id) {
        countryRepository.deleteById(id);
    }
}
