package com.example.demo.dto.bedding;

import com.example.demo.model.Bedding;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeddingDtoOutput {

  private Long id;

  private String description;

  private Integer maxCapacity;

  public BeddingDtoOutput(Bedding bedding) {
    this.id = bedding.getId();
    this.description = bedding.getDescription();
    this.maxCapacity = bedding.getMaxCapacity();
  }
}
