package com.example.demo.controller;


import com.example.demo.dto.room.RoomDtoInput;
import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
@RequestMapping(path = "/rooms")
@RestController
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity add(@RequestBody RoomDtoInput roomDtoInput) {

        RoomDtoOutput newRoom = roomService.add(roomDtoInput);

        return ResponseEntity.ok(newRoom);
    }

    @GetMapping
    public ResponseEntity selectAll() {
        List<RoomDtoOutput> rooms = roomService.getAll();

        return ResponseEntity.ok(rooms);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectById(@PathVariable Long id) {
        Optional<Room> selectedRoom = roomService.getById(id);
//    selectedRoom.orElseThrow(() -> new NotFoundException(Constants.ROOM_NOT_FOUND));   //to do

        return ResponseEntity.ok(new RoomDtoOutput(selectedRoom.get()));
    }

    @GetMapping(path = "/hotel/{id}")
    public ResponseEntity selectByHotelId(@PathVariable Long id) {
        List<RoomDtoOutput> rooms = roomService.getByHotelId(id);

        return ResponseEntity.ok(rooms);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity replace(@PathVariable Long id, @RequestBody RoomDtoInput roomDtoInput) {

        RoomDtoOutput updatedRoom = roomService.replace(id, roomDtoInput);

        //to do  Implement exceptions for not found cases (on service layer)
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Optional<Room> deleted = roomService.delete(id);
        if (!deleted.isPresent()) {
            Map<String, String> result = new HashMap<String, String>();
//        result.put("message", Constants.ROOM_NOT_FOUND);       // to do   Is this way ok or is it better handled with exceptions?

            return ResponseEntity.status(404).body(result);
        }

        return ResponseEntity.ok(new RoomDtoOutput(deleted.get()));
    }

}
