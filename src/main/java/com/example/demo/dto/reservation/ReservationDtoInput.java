package com.example.demo.dto.reservation;

import com.example.demo.util.Constants;
import com.example.demo.util.ErrorMessage;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDtoInput {

  @NotNull(message = ErrorMessage.NOT_NULL)
  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Long roomId;

  @NotNull(message = ErrorMessage.NOT_NULL)
  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Long userId;

  @NotBlank(message = ErrorMessage.NOT_BLANK)
  @Pattern(message = ErrorMessage.INVALID_DATE_FORMAT, regexp = Constants.DATE_VALIDATION_REGEX)
  private String checkIn;

  @NotBlank(message = ErrorMessage.NOT_BLANK)
  @Pattern(message = ErrorMessage.INVALID_DATE_FORMAT, regexp = Constants.DATE_VALIDATION_REGEX)
  private String checkOut;

  @NotNull(message = ErrorMessage.NOT_NULL)
  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Double finalPrice;

  @NotNull(message = ErrorMessage.NOT_NULL)
  private Boolean isPaid;
}
