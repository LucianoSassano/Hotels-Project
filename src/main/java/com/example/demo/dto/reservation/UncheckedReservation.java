package com.example.demo.dto.reservation;

import com.example.demo.util.Constants;
import com.example.demo.util.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UncheckedReservation {

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Long roomId;

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Long userId;

  @JsonFormat(pattern = Constants.DATE_PATTERN)
  private LocalDate checkIn;

  @JsonFormat(pattern = Constants.DATE_PATTERN)
  private LocalDate checkOut;

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Double finalPrice;

  private Boolean isPaid;
}
