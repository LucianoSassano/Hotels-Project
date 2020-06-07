package com.example.demo.dto;

import com.example.demo.model.Country;
import com.example.demo.model.Estate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CountryDto {

    private String name;
    private List<Estate> states;

    public CountryDto(Country country){
        this.name = country.getName();
        this.states = country.getEstates();
    }
}
