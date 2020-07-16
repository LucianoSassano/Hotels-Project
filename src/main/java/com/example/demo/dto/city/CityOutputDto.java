package com.example.demo.dto.city;

import com.example.demo.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityOutputDto {

  private Long id;

  private String name;

  private Integer zipCode;

  private Boolean isDeleted;

  public CityOutputDto(City city) {
    this.id = city.getId();
    this.name = city.getName();
    this.zipCode = city.getZip_code();
    this.isDeleted = city.getIsDeleted();
  }
}
