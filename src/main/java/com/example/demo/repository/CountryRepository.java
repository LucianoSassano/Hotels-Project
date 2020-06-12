package com.example.demo.repository;

import com.example.demo.dto.CountryDto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CountryRepository extends JpaRepository<CountryDto, Integer> {

}
