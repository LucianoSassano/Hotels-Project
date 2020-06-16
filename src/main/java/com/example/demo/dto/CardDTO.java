package com.example.demo.dto;
import com.example.demo.model.CreditCard;
import com.example.demo.util.UserExceptionMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO implements Serializable {
    @NotBlank(message= UserExceptionMessages.NOT_BLANK)
    @NotNull(message=UserExceptionMessages.NOT_NULL)
    private Long number;

    public static CardDTO generateInstanceFromEntity(CreditCard card){
        return new CardDTO().builder()
                .number(card.getNumber())
                .build();
    }
}
