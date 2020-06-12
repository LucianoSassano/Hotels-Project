package com.example.demo.dto.reservation;


import com.example.demo.util.Constants;
import com.example.demo.util.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDtoInput {

    @NotNull(message = ErrorMessage.NOT_NULL)
    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Long roomId;

    @NotBlank(message = ErrorMessage.NOT_BLANK)
    @Pattern(message = ErrorMessage.INVALID_DATE_FORMAT, regexp = Constants.DATE_VALIDATION_REGEX)
    private String checkIn;

    @NotBlank(message = ErrorMessage.NOT_BLANK)
    @Pattern(message = ErrorMessage.INVALID_DATE_FORMAT, regexp = Constants.DATE_VALIDATION_REGEX)
    private String checkOut;

    @NotNull(message = ErrorMessage.NOT_NULL)
    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Double finalPrice;

    @NotNull(message = ErrorMessage.NOT_NULL)
    private Boolean isPaid;

}
