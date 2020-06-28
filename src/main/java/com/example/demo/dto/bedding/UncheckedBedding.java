package com.example.demo.dto.bedding;

import com.example.demo.util.ErrorMessage;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UncheckedBedding {

  @Size(min = 1, max = 50, message = ErrorMessage.INVALID_STRING_SIZE)
  private String description;

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Integer maxCapacity;
}
