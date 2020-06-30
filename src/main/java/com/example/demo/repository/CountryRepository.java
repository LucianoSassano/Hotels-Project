package com.example.demo.repository;

import com.example.demo.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

  @Modifying
  @Query(value = "UPDATE country c SET is_deleted=false WHERE c.id = :id ", nativeQuery = true)
  void restoreCountryById(@Param("id") Long id);
}
