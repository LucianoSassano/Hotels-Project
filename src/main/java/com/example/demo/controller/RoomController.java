package com.example.demo.controller;

import com.example.demo.dto.room.RoomDtoInput;
import com.example.demo.dto.room.RoomDtoOutput;
import com.example.demo.dto.room.UncheckedRoom;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RequestMapping(path = "/rooms")
@RestController
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody RoomDtoInput roomDtoInput) {
        return ResponseEntity.ok(roomService.add(roomDtoInput));
    }

    @GetMapping
    public ResponseEntity selectAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity selectById(@PathVariable Long id) {
        return ResponseEntity.ok(new RoomDtoOutput(roomService.getById(id)));
    }

    @GetMapping(path = "/hotel/{id}")
    public ResponseEntity selectByHotelId(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getByHotelId(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity replace(@PathVariable Long id, @Valid @RequestBody RoomDtoInput roomDtoInput) {
        return ResponseEntity.ok(roomService.replace(id, roomDtoInput));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.ok(new RoomDtoOutput(roomService.delete(id)));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity partialUpdate(@PathVariable Long id, @Valid @RequestBody UncheckedRoom uncheckedRoom) {
        return ResponseEntity.ok(roomService.partialUpdate(id, uncheckedRoom));
    }


}
