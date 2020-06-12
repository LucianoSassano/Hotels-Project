package com.example.demo.repository;

import com.example.demo.dto.CityDto;


import org.springframework.data.jpa.repository.JpaRepository;


public interface CityRepository extends JpaRepository<CityDto, Integer> {


}
