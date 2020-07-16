package com.example.demo.dto.country;

import com.example.demo.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryOutputDto {

  private Long id;

  private String name;

  private Boolean isDeleted;

  public CountryOutputDto(Country country) {
    this.id = country.getId();
    this.name = country.getName();
    this.isDeleted = country.getIsDeleted();
  }
}
