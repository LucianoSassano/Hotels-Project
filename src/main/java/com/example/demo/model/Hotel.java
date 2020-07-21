package com.example.demo.model;

import com.example.demo.dto.hotel.HotelDtoInput;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE hotels SET is_deleted = true WHERE hotel_id=?")
@Where(clause = "is_deleted = false")
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "hotel_id")
  private Long id;

  private String name;

  private String address;

  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  @Column(unique = true)
  private String email;

  private String phone;

  private Integer roomCapacity;

  private Long rating;

  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  private List<Room> rooms;

  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  private List<Reservation> reservations;

  @NotNull private Boolean isDeleted;

  @PrePersist
  @PreUpdate
  void preInsert() {
    if (this.isDeleted == null) this.isDeleted = false;
  }

  public static Hotel buildHotelEntity(HotelDtoInput hotelDtoInput) {
    return Hotel.builder()
        .name(hotelDtoInput.getName())
        .address(hotelDtoInput.getAddress())
        .city(City.builder().id(hotelDtoInput.getCityId()).build())
        .email(hotelDtoInput.getEmail())
        .phone(hotelDtoInput.getPhone())
        .roomCapacity(hotelDtoInput.getRoomCapacity())
        .rating(hotelDtoInput.getRating())
        .build();
  }
}
