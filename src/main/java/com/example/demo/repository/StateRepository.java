package com.example.demo.repository;

import com.example.demo.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StateRepository extends JpaRepository<Estate, Integer> {

    @Modifying
    @Query(value = "UPDATE state  SET is_deleted=false WHERE s.id = :id ", nativeQuery = true)
    void restoreStateById(@Param("id") Integer id);


}
