package com.hocine.schoolmanager.controller;

import com.hocine.schoolmanager.model.Teacher;
import com.hocine.schoolmanager.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {
    private final TeacherService service;
    public TeacherController(TeacherService service) { this.service = service; }

    @GetMapping public List<Teacher> all() { return service.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Teacher> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Teacher create(@RequestBody Teacher t) { return service.save(t); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
}
