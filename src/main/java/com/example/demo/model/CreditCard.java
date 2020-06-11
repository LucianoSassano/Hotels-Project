
package com.example.demo.model;
import com.example.demo.dto.CardDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "credit_cards")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long user_id;
    private Integer number;

    @NotNull
    private LocalDateTime createAt;

    @NotNull
    private LocalDateTime updateAt;

    @NotNull
    private LocalDateTime deleteAt;

    public static CreditCard generateInstanceFromDTO(CardDTO cardDto){
        return  CreditCard.builder()
                .user_id(cardDto.getUser_id())
                .number(cardDto.getNumber())
                .build();
    }
}





/*package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Integer number;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User users;

}*/
