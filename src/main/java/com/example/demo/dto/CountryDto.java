package com.example.demo.dto;

import com.example.demo.model.Country;
import com.example.demo.model.Estate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CountryDto {

    private Integer id;
    private String name;
    private List<Estate> states;

    public CountryDto(Country country){
        this.id = country.getId();
        this.name = country.getName();
        this.states = country.getEstates();
    }

    public void setEstates(List<Estate> estates) {
        this.states = estates;
    }
}
