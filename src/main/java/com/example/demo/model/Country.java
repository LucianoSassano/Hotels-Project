package com.example.demo.model;


import com.example.demo.dto.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE country SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false ")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "country")
    private List<Estate> estates;

    @NotNull
    private Boolean isDeleted;

    @PrePersist
    @PreUpdate
    void preInsert() {
        if (this.isDeleted == null) {
            this.isDeleted = false;
        }
    }

    public static Country buildCountryEntity(CountryDto countryDto) {
        return Country.builder()
                .id(countryDto.getId())
                .name(countryDto.getName())
                .estates(countryDto.getStates())
                .build();
    }


}
