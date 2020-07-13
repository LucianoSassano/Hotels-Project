package com.example.demo.dto.city;

import com.example.demo.model.City;
import com.example.demo.util.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityInputDto {

  @NotNull(message = ErrorMessage.NOT_NULL)
  @PositiveOrZero(message = ErrorMessage.NOT_POSITIVE)
  private Long id;

  @NotNull(message = ErrorMessage.NOT_NULL)
  @Size(max = 30, message = ErrorMessage.INVALID_STRING_SIZE)
  private String name;

  @NotNull(message = ErrorMessage.NOT_NULL)
  @PositiveOrZero(message = ErrorMessage.NOT_POSITIVE)
  private Integer zipCode;

  @NotNull(message = ErrorMessage.NOT_NULL)
  private Long stateId;

  public CityInputDto(City city) {

    this.id = city.getId();
    this.name = city.getName();
    this.zipCode = city.getZip_code();
    this.stateId = city.getStateId();
  }
}
