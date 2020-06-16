package com.example.demo.model;
import com.example.demo.dto.UserDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET delete_at = true WHERE id=?")
@Where(clause = "delete_at = false")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<CreditCard> cards;

    private String name;
    private String address;
    @Column(unique = true,updatable = false)
    private Integer dni;
    private String email;
    private Long phone;


    @NotNull
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createAt;

    @NotNull
    @UpdateTimestamp
    private LocalDateTime updateAt;

    private Boolean deleteAt;
    @PrePersist
    void preInsert() {
        if (this.deleteAt == null)
            this.deleteAt = false;
    }
    private Role rol;

    public static User generateInstanceFromDTO(UserDTO adn){
        return User.builder().address(adn.getAddress())
                //.cards(CardsUtils.setCardsDTOtoEntity(adn.getCards()))
                .email(adn.getEmail())
                .dni(adn.getDni())
                .name(adn.getName())
                .phone(adn.getPhone())
                .rol(adn.getRol())
                .build();

    }
}
