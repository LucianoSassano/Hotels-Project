package com.example.demo.util;

import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomUtils {

  public static List<RoomDtoOutput> listEntityToDTO(List<Room> rooms) {
    return rooms.stream().map(RoomDtoOutput::new).collect(Collectors.toList());
  }
}
