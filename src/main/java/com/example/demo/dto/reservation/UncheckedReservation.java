package com.example.demo.dto.reservation;

import com.example.demo.util.Constants;
import com.example.demo.util.ErrorMessage;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UncheckedReservation {

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Long roomId;

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Long userId;

  @Pattern(message = ErrorMessage.INVALID_DATE_FORMAT, regexp = Constants.DATE_VALIDATION_REGEX)
  private String checkIn;

  @Pattern(message = ErrorMessage.INVALID_DATE_FORMAT, regexp = Constants.DATE_VALIDATION_REGEX)
  private String checkOut;

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Double finalPrice;

  private Boolean isPaid;
}
