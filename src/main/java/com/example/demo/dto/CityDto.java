package com.example.demo.dto;

import com.example.demo.model.City;
import com.example.demo.model.Estate;
import com.example.demo.util.ErrorMessage;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CityDto {

    @NotNull(message = ErrorMessage.NOT_NULL)
    @PositiveOrZero(message = ErrorMessage.NOT_POSITIVE)
    private Integer id;

    @NotNull(message = ErrorMessage.NOT_NULL)
    @Size(max = 30, message = ErrorMessage.INVALID_STRING_SIZE)
    private String name;

    @NotNull(message = ErrorMessage.NOT_NULL)
    @PositiveOrZero(message = ErrorMessage.NOT_POSITIVE)
    private Integer zipCode;

    @NotNull(message = ErrorMessage.NOT_NULL)
    private Estate state;

    public CityDto(City city) {

        this.id = city.getId();
        this.name = city.getName();
        this.zipCode = city.getZip_code();
        this.state = city.getState();
    }

}
