package com.example.demo.dto.room;

import com.example.demo.model.Category;
import com.example.demo.util.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UncheckedRoom {

    private Category category;

    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Long beddingId;

    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Long hotelId;

    private Boolean status;

    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Double dailyRate;

}
