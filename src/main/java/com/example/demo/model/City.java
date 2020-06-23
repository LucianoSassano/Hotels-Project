package com.example.demo.model;

import com.example.demo.dto.CityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE city SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false ")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private Integer zip_code;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private Estate state;

    @NotNull
    private Boolean isDeleted;

    @PrePersist
    @PreUpdate
    void preInsert() {
        if (this.isDeleted == null) {
            this.isDeleted = false;
        }
    }


    public static City buildCityEntity(CityDto cityDto) {
        return City.builder()
                .name(cityDto.getName())
                .id(cityDto.getId())
                .state(cityDto.getState())
                .zip_code(cityDto.getZipCode())
                .build();
    }


}
