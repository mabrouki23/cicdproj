package com.hocine.schoolmanager.service;

import com.hocine.schoolmanager.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student save(Student s);
    void delete(Long id);
    Student enrollInCourse(Long studentId, Long courseId);
}
