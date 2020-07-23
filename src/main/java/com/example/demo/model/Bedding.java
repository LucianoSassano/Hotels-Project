package com.example.demo.model;

import com.example.demo.dto.bedding.BeddingDtoInput;
import com.example.demo.dto.bedding.UncheckedBedding;
import com.example.demo.model.common.CustomDbUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "beddings")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE beddings SET is_deleted = true WHERE bedding_id=?")
@Where(clause = "is_deleted = false")
public class Bedding implements CustomDbUtils {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bedding_id")
  private Long id;

  private String description;

  @OneToMany(mappedBy = "bedding", cascade = CascadeType.ALL)
  private List<Room> rooms;

  private Integer maxCapacity;

  @NotNull protected Boolean isDeleted;

  @PrePersist
  @PreUpdate
  protected void preInsert() {
    if (this.isDeleted == null) this.isDeleted = false;
  }

  public static Bedding buildBeddingEntity(BeddingDtoInput beddingDtoInput) {

    return Bedding.builder()
        .description(beddingDtoInput.getDescription())
        .maxCapacity(beddingDtoInput.getMaxCapacity())
        .build();
  }

  public void update(UncheckedBedding dtoPatch) {
    setIfNotNull(this::setDescription, dtoPatch.getDescription());
    setIfNotNull(this::setMaxCapacity, dtoPatch.getMaxCapacity());
  }
}
