package com.example.demo.dto.bedding;

import com.example.demo.util.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UncheckedBedding {

    @Size(min = 1, max = 50, message = ErrorMessage.INVALID_STRING_SIZE)
    private String description;

    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Integer maxCapacity;

}
