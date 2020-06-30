package com.example.demo.model;

import com.example.demo.dto.reservation.ReservationDtoInput;

import com.example.demo.util.Constants;
import com.example.demo.util.SharedUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE reservations SET is_deleted = true WHERE reservation_id=?")
@Where(clause = "is_deleted = false")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reservation_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  @ManyToOne
  @JoinColumn(name = "room_id")
  private Room room;

  private LocalDate checkIn;

  private LocalDate checkOut;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp private LocalDateTime updatedAt;

  private Double finalPrice;

  private Boolean isPaid;

  @NotNull private Boolean isDeleted;

  @PrePersist
  @PreUpdate
  void preInsert() {
    if (this.isDeleted == null) this.isDeleted = false;
  }

  public static Reservation buildReservationEntity(ReservationDtoInput reservationDtoInput) {
    return Reservation.builder()
        .room(Room.builder().id(reservationDtoInput.getRoomId()).build())
        .user(User.builder().id(reservationDtoInput.getUserId()).build())
        .checkIn(
            LocalDate.parse(
                SharedUtils.adjustLocalDate(reservationDtoInput.getCheckIn()),
                DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)))
        .checkOut(
            LocalDate.parse(
                SharedUtils.adjustLocalDate(reservationDtoInput.getCheckOut()),
                DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)))
        .finalPrice(reservationDtoInput.getFinalPrice())
        .isPaid(reservationDtoInput.getIsPaid())
        .build();
  }
}
