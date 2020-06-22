package com.example.demo.dto.room;

import com.example.demo.dto.bedding.BeddingDto;
import com.example.demo.dto.hotel.HotelDtoOutput;
import com.example.demo.model.Category;
import com.example.demo.model.Room;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoOutput {

  private Long id;

  private Category category;

  private BeddingDto bedding;

  @JsonManagedReference private HotelDtoOutput hotel;

  private Boolean status;

  private Double dailyRate;

  public RoomDtoOutput(Room room) {
    this.id = room.getId();
    this.category = room.getCategory();
    this.bedding = new BeddingDto(room.getBedding());
    this.hotel = new HotelDtoOutput(room.getHotel());
    this.status = room.getStatus();
    this.dailyRate = room.getDailyRate();
  }
}
