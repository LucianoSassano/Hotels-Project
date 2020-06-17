package com.example.demo.model;

import com.example.demo.dto.bedding.BeddingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "beddings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bedding {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bedding_id")
  private Long id;

  private String description;

  @OneToMany(mappedBy = "bedding", cascade = CascadeType.ALL)
  private List<Room> rooms;

  private Integer maxCapacity;

  public static Bedding buildBeddingEntity(BeddingDto beddingDto) {

    return Bedding.builder()
        .description(beddingDto.getDescription())
        .maxCapacity(beddingDto.getMaxCapacity())
        .build();
  }
}
