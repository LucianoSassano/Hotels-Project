package com.example.demo.model;

import com.example.demo.dto.room.RoomDtoInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE rooms SET is_deleted = true WHERE room_id=?")
@Where(clause = "is_deleted = false")
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "room_id")
  private Long id;

  @Enumerated(EnumType.STRING)
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bedding_id")
  private Bedding bedding;

  private Boolean status;

  private Double dailyRate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
  private List<Reservation> reservations;

  @NotNull private Boolean isDeleted;

  @PrePersist
  @PreUpdate
  void preInsert() {

    if (this.isDeleted == null) this.isDeleted = false;
  }

  public static Room buildRoomEntity(RoomDtoInput roomDtoInput) {

    return Room.builder()
        .category(roomDtoInput.getCategory())
        .bedding(Bedding.builder().id(roomDtoInput.getBeddingId()).build())
        .hotel(Hotel.builder().id(roomDtoInput.getHotelId()).build())
        .status(roomDtoInput.getStatus())
        .dailyRate(roomDtoInput.getDailyRate())
        .build();
  }
}
