package org.tutorial.dio.saladereuniao.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutorial.dio.saladereuniao.model.Room;
import org.tutorial.dio.saladereuniao.service.RoomService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable("id") Long roomId) {
        return roomService.findById(roomId);
    }

    @GetMapping("/test/{id}")
    public boolean test(@PathVariable("id") Long roomId) {
        return roomService.containsId(roomId);
    }

    @PostMapping
    public Map<String, Room> saveRoom(@Validated @RequestBody Room room) {
        return Map.of("created meeting room", roomService.save(room));
    }

    @PutMapping("/{id}")
    public Map<String, Room> updateRoom(@Validated @RequestBody Room room, @PathVariable Long id) {
        return Map.of("updated meeting room", roomService.updateRoom(room, id));
    }

    @DeleteMapping("/{id}")
    public Map<String, Room> deleteRoom(@PathVariable Long id) {
        return Map.of("deleted meeting room", roomService.deleteRoom(id));
    }

}
