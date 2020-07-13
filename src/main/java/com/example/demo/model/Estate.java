package com.example.demo.model;

import com.example.demo.dto.state.EstateInputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "state")
@SQLDelete(sql = "UPDATE state SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false ")
public class Estate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String name;

  @NotNull @PositiveOrZero private Long countryId;

  @NotNull private Boolean isDeleted;

  @PrePersist
  @PreUpdate
  void preInsert() {
    if (this.isDeleted == null) {
      this.isDeleted = false;
    }
  }

  public static Estate buildEstateEntity(EstateInputDto estateInputDto) {

    return Estate.builder()
        .name(estateInputDto.getName())
        .countryId(estateInputDto.getCountryId())
        .build();
  }
}
