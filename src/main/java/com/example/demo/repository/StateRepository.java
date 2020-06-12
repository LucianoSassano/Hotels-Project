package com.example.demo.repository;

import com.example.demo.dto.EstateDto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StateRepository extends JpaRepository<EstateDto, Integer> {


}
