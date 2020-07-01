package com.example.demo.service;

import com.example.demo.dto.country.CountryInputDto;
import com.example.demo.dto.country.CountryOutputDto;
import com.example.demo.exception.DuplicateEntryException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.util.ErrorMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

  @Transactional
  public Country add(CountryInputDto countryDto) {
    Country countryToAdd = Country.buildCountryEntity(countryDto);

    countryRepository
        .findCountryById(countryDto.getId())
        .ifPresent(
            country -> {
              if (country.getIsDeleted()) {
                countryRepository.restoreCountryById(country.getId());
              } else throw new DuplicateEntryException(ErrorMessage.DUPLICATE_ENTRY);
            });

    if (countryRepository.findById(countryDto.getId()).isEmpty()) {
      countryRepository.save(countryToAdd);
    }

    return countryToAdd;
  }

  public Country getById(Long id) {

    return countryRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.COUNTRY_NOT_FOUND));
  }

  public Country updateCountry(Long id, CountryInputDto countryDto) {
    countryRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.COUNTRY_NOT_FOUND));
    Country updatedCountry = Country.buildCountryEntity(countryDto);
    updatedCountry.setId(id);
    return countryRepository.save(updatedCountry);
  }

  public Country deleteCountryById(Long id) {
    Country countryToDelete =
        countryRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.COUNTRY_NOT_FOUND));
    countryToDelete.setIsDeleted(true);
    countryRepository.save(countryToDelete);
    return countryToDelete;
  }
}
