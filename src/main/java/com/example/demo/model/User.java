package com.example.demo.model;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserDtoInsert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JoinColumn(name = "userId")
  @OneToMany(cascade = CascadeType.PERSIST)
  private List<CreditCard> cards;

  private String name;
  private String address;

  @NotNull
  @NotBlank
  private String password;

  @Column(unique = true, updatable = false)
  private Integer dni;

  private String email;
  private Long phone;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp private LocalDateTime updateAt;

  private Boolean isDeleted;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Reservation> reservations;

  @PrePersist
  void preInsert() {
    if (this.isDeleted == null) this.isDeleted = false;
  }

  private Role rol;

  public static User generateInstanceFromDTO(UserDTO adn) {
    return User.builder()
        .address(adn.getAddress())
        .email(adn.getEmail())
        .dni(adn.getDni())
        .name(adn.getName())
        .phone(adn.getPhone())
        .rol(adn.getRol())
        .build();
  }
    public static User generateInstanceFromDtoInsert(UserDtoInsert adn) {
        return User.builder()
                .address(adn.getAddress())
                .email(adn.getEmail())
                .password(adn.getPassword())
                .dni(adn.getDni())
                .name(adn.getName())
                .phone(adn.getPhone())
                .rol(adn.getRol())
                .build();
    }
}
