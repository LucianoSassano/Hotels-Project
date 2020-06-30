package com.example.demo.dto.hotel;

import com.example.demo.util.ErrorMessage;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDtoInput {

  @NotBlank(message = ErrorMessage.NOT_BLANK)
  @Size(min = 1, max = 50, message = ErrorMessage.INVALID_STRING_SIZE)
  private String name;

  @NotBlank(message = ErrorMessage.NOT_BLANK)
  @Size(min = 1, max = 50, message = ErrorMessage.INVALID_STRING_SIZE)
  private String address;

  @NotNull(message = "cityId cannot be null")
  @Positive(message = "cityId must be a positive number")
  private Long cityId;

  @NotBlank(message = ErrorMessage.NOT_BLANK)
  @Email(message = ErrorMessage.INVALID_EMAIL)
  private String email;

  @NotBlank(message = ErrorMessage.NOT_BLANK)
  @Size(min = 1, max = 50, message = ErrorMessage.INVALID_STRING_SIZE)
  private String phone;

  @NotNull(message = ErrorMessage.NOT_NULL)
  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Integer roomCapacity;

  @NotNull(message = ErrorMessage.NOT_NULL)
  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Long rating;
}
