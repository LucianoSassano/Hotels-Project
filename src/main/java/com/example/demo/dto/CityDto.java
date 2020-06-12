package com.example.demo.dto;

import com.example.demo.model.City;
import com.example.demo.model.Estate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityDto {

    private Integer id;
    private String name;
    private Integer zipCode;
    private Estate state;

    public CityDto(City city){

        this.id = city.getId();
        this.name = city.getName();
        this.zipCode = city.getZipCode();
        this.state = city.getState();
    }

}
