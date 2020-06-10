package com.example.demo.dto.bedding;

import com.example.demo.model.Bedding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeddingDto {

    private String description;

    private Integer maxCapacity;

    public BeddingDto(Bedding bedding) {
        this.description = bedding.getDescription();
        this.maxCapacity = bedding.getMaxCapacity();
    }
}
