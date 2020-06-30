package com.example.demo.repository;

import com.example.demo.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT * FROM city c WHERE c.zip_code = :zip_code ", nativeQuery = true)
    Optional<City> findCityByZip(@Param("zip_code") Integer zip_code);


    @Modifying
    @Query(value = "UPDATE city c SET is_deleted=false WHERE c.id = :id ", nativeQuery = true)
    void restoreCityById(@Param("id") Long id);
}
