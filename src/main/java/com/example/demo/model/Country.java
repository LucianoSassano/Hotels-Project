package com.example.demo.model;

import com.example.demo.dto.country.CountryInputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
  private Long id;

  @Column(unique = true)
  private String name;

  @NotNull private Boolean isDeleted;

  @PreUpdate
  @PrePersist
  public void preInsert() {
    if (this.isDeleted == null) {
      this.isDeleted = false;
    }
  }

  public static Country buildCountryEntity(CountryInputDto countryDto) {
    return Country.builder().id(countryDto.getId()).name(countryDto.getName()).build();
  }
}
