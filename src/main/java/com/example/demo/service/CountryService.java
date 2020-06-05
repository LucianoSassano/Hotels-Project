package com.example.demo.service;


import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> listAllCountries() {
        return countryRepository.findAll();
    }

    public void save(Country country) {
        countryRepository.save(country);
    }

    public Country get(Integer id) {
        return countryRepository.findById(id).get();
    }

    public void delete(Integer id) {
        countryRepository.deleteById(id);
    }
}
