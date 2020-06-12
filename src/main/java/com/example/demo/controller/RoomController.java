package com.example.demo.controller;


import com.example.demo.dto.room.RoomDtoInput;
import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping(path = "/rooms")
@RestController
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody RoomDtoInput roomDtoInput) {

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
        Room selectedRoom = roomService.getById(id);

        return ResponseEntity.ok(new RoomDtoOutput(selectedRoom));
    }

    @GetMapping(path = "/hotel/{id}")
    public ResponseEntity selectByHotelId(@PathVariable Long id) {
        List<RoomDtoOutput> rooms = roomService.getByHotelId(id);

        return ResponseEntity.ok(rooms);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity replace(@PathVariable Long id, @Valid @RequestBody RoomDtoInput roomDtoInput) {

        RoomDtoOutput updatedRoom = roomService.replace(id, roomDtoInput);

        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        Room deleted = roomService.delete(id);

        return ResponseEntity.ok(new RoomDtoOutput(deleted));
    }

}
