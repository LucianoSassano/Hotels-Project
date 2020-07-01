package com.example.demo.repository;

import com.example.demo.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StateRepository extends JpaRepository<Estate, Long> {

  @Query(value = "SELECT * FROM state s WHERE s.id = :id ", nativeQuery = true)
  Optional<Estate> findStateById(@Param("id") Long id);

  @Modifying
  @Query(value = "UPDATE state s SET is_deleted=false WHERE s.id =:id ", nativeQuery = true)
  void restoreStateById(@Param("id") Long id);
}
