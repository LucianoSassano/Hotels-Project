package com.example.demo.dto.bedding;

import com.example.demo.model.Bedding;
import com.example.demo.util.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeddingDto {

    @NotBlank(message = ErrorMessage.NOT_BLANK)
    @Size(min = 1, max = 50, message = ErrorMessage.INVALID_STRING_SIZE)
    private String description;

    @NotNull(message = ErrorMessage.NOT_NULL)
    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Integer maxCapacity;

    public BeddingDto(Bedding bedding) {
        this.description = bedding.getDescription();
        this.maxCapacity = bedding.getMaxCapacity();
    }
}
