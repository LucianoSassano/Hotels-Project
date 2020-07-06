package com.example.demo.dto.state;

import com.example.demo.model.Estate;
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
public class EstateInputDto {

  @NotNull(message = ErrorMessage.NOT_NULL)
  @PositiveOrZero(message = ErrorMessage.NOT_POSITIVE)
  private Long id;

  @NotNull(message = ErrorMessage.NOT_NULL)
  @Size(max = 30, message = ErrorMessage.INVALID_STRING_SIZE)
  private String name;

  @NotNull(message = ErrorMessage.NOT_NULL)
  private Long countryId;

  public EstateInputDto(Estate state) {
    this.id = state.getId();
    this.name = state.getName();
    this.countryId = state.getCountryId();
  }
}
