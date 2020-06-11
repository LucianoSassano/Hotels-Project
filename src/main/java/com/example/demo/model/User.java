package com.example.demo.model;
import com.example.demo.dto.UserDTO;
import com.example.demo.util.CardsUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.PERSIST,orphanRemoval = true)
    private Set<CreditCard> cards;

    private String name;
    private String address;
    private String email;
    private Long phone;


    @NotNull
    private LocalDateTime createAt;

    @NotNull
    private LocalDateTime updateAt;

    @NotNull
    private LocalDateTime deleteAt;

    private Role rol;

    public static User generateInstanceFromDTO(UserDTO adn){
        return User.builder().address(adn.getAddress())
                .cards(CardsUtils.setCardsDTOtoEntity(adn.getCards()))
                .email(adn.getEmail())
                .name(adn.getName())
                .phone(adn.getPhone())
                .rol(adn.getRol())
                .build();

    }


}