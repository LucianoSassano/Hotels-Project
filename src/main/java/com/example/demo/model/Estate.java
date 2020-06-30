package com.example.demo.model;

import com.example.demo.dto.state.EstateInputDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "state")
  private List<City> cities;

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
        .country(Country.builder().id(estateInputDto.getCountryId()).build())
        .build();
  }
}
