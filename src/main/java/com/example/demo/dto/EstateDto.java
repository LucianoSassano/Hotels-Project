package com.example.demo.dto;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.model.Estate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EstateDto {

    private Integer id;
    private String name;
    private Country country;
    private List<City> cities;

    public EstateDto(Estate state) {
        this.id = state.getId();
        this.name = state.getName();
        this.country = state.getCountry();
        this.cities = state.getCities();

    }

}
