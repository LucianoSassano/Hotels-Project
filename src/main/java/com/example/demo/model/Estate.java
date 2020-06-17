package com.example.demo.model;


import com.example.demo.dto.EstateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "state")
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "state")
    private List<City> cities;

    public static Estate buildEstateEntity(EstateDto estateDto) {
        return Estate.builder()
                .id(estateDto.getId())
                .name(estateDto.getName())
                .country(estateDto.getCountry())
                .cities(estateDto.getCities())
                .build();
    }


}
