package com.robotdr.hotel_api.rest;

import com.robotdr.hotel_api.domain.Room;
import com.robotdr.hotel_api.dto.RoomDto;
import com.robotdr.hotel_api.servis.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDto>> findAll() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> findById(@PathVariable Long id) {
        return roomService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/rooms")
    public ResponseEntity<Void> save(@RequestBody Room room) {
        roomService.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/rooms/{dataStart}/{dataEnd}")
    public ResponseEntity<List<RoomDto>> findFreeRoom(@PathVariable LocalDate dataStart, @PathVariable LocalDate dataEnd) {
        return ResponseEntity.ok(roomService.findFreeRoom(dataStart, dataEnd));
    }

}
