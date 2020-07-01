package com.example.demo.dto.state;

import com.example.demo.model.Estate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstateOutputDto {

  private Long id;

  private String name;

  private Long countryId;

  private Boolean isDeleted;

  public EstateOutputDto(Estate estate) {
    this.id = estate.getId();
    this.name = estate.getName();
    this.countryId = estate.getCountry().getId();
    this.isDeleted = estate.getIsDeleted();
  }
}
