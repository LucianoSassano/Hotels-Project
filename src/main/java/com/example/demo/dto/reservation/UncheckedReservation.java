package com.example.demo.dto.reservation;


import com.example.demo.util.Constants;
import com.example.demo.util.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UncheckedReservation {

    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Long roomId;

//    @Positive(message = ErrorMessage.NOT_POSITIVE)
//    private Long userId;

    @Pattern(message = ErrorMessage.INVALID_DATE_FORMAT, regexp = Constants.DATE_VALIDATION_REGEX)
    private String checkIn;

    @Pattern(message = ErrorMessage.INVALID_DATE_FORMAT, regexp = Constants.DATE_VALIDATION_REGEX)
    private String checkOut;

    @Positive(message = ErrorMessage.NOT_POSITIVE)
    private Double finalPrice;

    private Boolean isPaid;

}
