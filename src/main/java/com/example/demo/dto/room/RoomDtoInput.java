package com.example.demo.dto.room;

import com.example.demo.model.Category;
import com.example.demo.util.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoInput {

    @NotNull(message = ErrorMessage.NOT_NULL)
    private Category category;

    @NotNull(message = ErrorMessage.NOT_NULL)
    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Long beddingId;

    @NotNull(message = ErrorMessage.NOT_NULL)
    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Long hotelId;

    @NotNull(message = ErrorMessage.NOT_NULL)
    private Boolean status;

    @NotNull(message = ErrorMessage.NOT_NULL)
    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Double dailyRate;

}
