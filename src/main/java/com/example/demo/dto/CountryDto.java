package com.example.demo.dto;

import com.example.demo.model.Country;
import com.example.demo.model.Estate;
import com.example.demo.util.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

  @NotNull(message = ErrorMessage.NOT_NULL)
  @PositiveOrZero(message = ErrorMessage.NOT_POSITIVE)
  private Long id;

  @NotNull(message = ErrorMessage.NOT_NULL)
  @Size(max = 30, message = ErrorMessage.INVALID_STRING_SIZE)
  private String name;

  @JsonIgnore
  @NotNull(message = ErrorMessage.NOT_NULL)
  @NotEmpty(message = ErrorMessage.EMPTY_COLLECTION)
  private List<Estate> states;

  public CountryDto(Country country) {
    this.id = country.getId();
    this.name = country.getName();
    this.states = country.getEstates();
  }
}
