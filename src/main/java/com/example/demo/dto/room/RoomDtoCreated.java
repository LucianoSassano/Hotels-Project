package com.example.demo.dto.room;

import com.example.demo.model.Category;
import com.example.demo.model.Room;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoCreated {

  private Long id;

  private Category category;

  private Long beddingId;

  private Long hotelId;

  private Boolean status;

  private Double dailyRate;

  public RoomDtoCreated(Room room) {
    this.id = room.getId();
    this.category = room.getCategory();
    this.beddingId = room.getBedding().getId();
    this.hotelId = room.getHotel().getId();
    this.status = room.getStatus();
    this.dailyRate = room.getDailyRate();
  }
}
