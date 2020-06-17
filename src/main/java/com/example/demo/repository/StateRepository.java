package com.example.demo.repository;


import com.example.demo.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StateRepository extends JpaRepository<Estate, Integer> {


}
