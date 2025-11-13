package com.hocine.schoolmanager.controller;

import com.hocine.schoolmanager.model.Room;
import com.hocine.schoolmanager.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {
    private final RoomService service;
    public RoomController(RoomService service) { this.service = service; }

    @GetMapping public List<Room> all() { return service.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Room> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Room create(@RequestBody Room r) { return service.save(r); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
}
