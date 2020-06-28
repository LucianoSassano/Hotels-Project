package com.example.demo.dto.room;

import com.example.demo.model.Category;
import com.example.demo.util.ErrorMessage;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UncheckedRoom {

  private Category category;

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Long beddingId;

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Long hotelId;

  private Boolean status;

  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Double dailyRate;
}
