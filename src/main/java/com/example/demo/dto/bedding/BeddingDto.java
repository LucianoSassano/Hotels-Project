package com.example.demo.dto.bedding;

import com.example.demo.model.Bedding;
import com.example.demo.util.ErrorMessage;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeddingDto {

  private Long id;

  @NotBlank(message = ErrorMessage.NOT_BLANK)
  @Size(min = 1, max = 50, message = ErrorMessage.INVALID_STRING_SIZE)
  private String description;

  @NotNull(message = ErrorMessage.NOT_NULL)
  @Positive(message = ErrorMessage.NOT_NEGATIVE)
  private Integer maxCapacity;

  public BeddingDto(Bedding bedding) {
    this.id = bedding.getId();
    this.description = bedding.getDescription();
    this.maxCapacity = bedding.getMaxCapacity();
  }
}
