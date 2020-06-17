package com.example.demo.model;

import com.example.demo.dto.CityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "zip_code")
    private Integer zip_code;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private Estate state;

    public static City buildCityEntity(CityDto cityDto) {
        return City.builder()
                .name(cityDto.getName())
                .id(cityDto.getId())
                .state(cityDto.getState())
                .zip_code(cityDto.getZipCode())
                .build();
    }


}
