package com.example.demo.service;


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

    public List<Country> listAllCountries() {
        return countryRepository.findAll();
    }

    public Country create(Country country){
        return countryRepository.save(country);
    }

    public Country getById(Integer id) {
        return countryRepository.findById(id).get();
    }

    public Country updateCountry(Country country){
        Optional<Country> countryDb = this.countryRepository.findById(country.getId());

        if(countryDb.isPresent()){
            Country countryUpdate = countryDb.get();
            countryUpdate.setId(country.getId());
            countryUpdate.setName(country.getName());
            countryUpdate.setEstates(country.getEstates());
            countryRepository.save(countryUpdate);
            return countryUpdate;
        }else{
            throw new RuntimeException();
        }
    }

    public void deleteCountryById(Integer id) {
        countryRepository.deleteById(id);
    }
}
