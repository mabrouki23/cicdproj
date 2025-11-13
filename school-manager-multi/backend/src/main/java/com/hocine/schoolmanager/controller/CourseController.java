package com.hocine.schoolmanager.controller;

import com.hocine.schoolmanager.model.Course;
import com.hocine.schoolmanager.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {
    private final CourseService service;
    public CourseController(CourseService service) { this.service = service; }

    @GetMapping public List<Course> all() { return service.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Course> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Course create(@RequestBody Course c) { return service.save(c); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }

    @PostMapping("/{courseId}/assign-teacher/{teacherId}")
    public Course assignTeacher(@PathVariable Long courseId, @PathVariable Long teacherId) {
        return service.assignTeacher(courseId, teacherId);
    }

    @PostMapping("/{courseId}/assign-room/{roomId}")
    public Course assignRoom(@PathVariable Long courseId, @PathVariable Long roomId) {
        return service.assignRoom(courseId, roomId);
    }
}
