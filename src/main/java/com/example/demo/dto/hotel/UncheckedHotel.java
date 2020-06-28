package com.example.demo.dto.hotel;

import com.example.demo.util.ErrorMessage;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UncheckedHotel {

  @Size(min = 1, max = 50, message = ErrorMessage.INVALID_STRING_SIZE)
  private String name;

  @Size(min = 1, max = 50, message = ErrorMessage.INVALID_STRING_SIZE)
  private String address;

  //    @Positive(message = "cityId must be a positive number")
  //    private Long cityId;

  @Email(message = ErrorMessage.INVALID_EMAIL)
  private String email;

  @Size(min = 1, max = 50, message = ErrorMessage.INVALID_STRING_SIZE)
  private String phone;

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Integer roomCapacity;

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Integer rating;
}
