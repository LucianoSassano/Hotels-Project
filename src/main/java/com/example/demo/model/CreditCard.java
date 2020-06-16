package com.example.demo.model;
import com.example.demo.dto.CardDTO;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "credit_cards")
@SQLDelete(sql = "UPDATE credit_cards SET delete_at = true WHERE id=?")
@Where(clause = "delete_at = false")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long user_id;

    @Column(unique = true,updatable = false)
    private Long number;

    @NotNull
    @Column(updatable = false)
    @CreationTimestamp
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

    public static CreditCard generateInstanceFromDTO(CardDTO cardDto){
        return  CreditCard.builder()
                .number(cardDto.getNumber())
                .build();
    }
}