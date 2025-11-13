package com.hocine.schoolmanager.controller;

import com.hocine.schoolmanager.model.Student;
import com.hocine.schoolmanager.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) { this.service = service; }

    @GetMapping public List<Student> all() { return service.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Student> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping public Student create(@RequestBody Student s) { return service.save(s); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }

    @PostMapping("/{id}/enroll/{courseId}")
    public Student enroll(@PathVariable Long id, @PathVariable Long courseId) { return service.enrollInCourse(id, courseId); }
}
