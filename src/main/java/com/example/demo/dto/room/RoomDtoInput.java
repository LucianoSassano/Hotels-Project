package com.example.demo.dto.room;

import com.example.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDtoInput {

    private Category category;

    private Long beddingId;

    private Long hotelId;

    private Boolean status;

    private Double dailyRate;

}
