package com.hocine.schoolmanager.service;

import com.hocine.schoolmanager.model.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Course save(Course c);
    void delete(Long id);
    Course assignTeacher(Long courseId, Long teacherId);
    Course assignRoom(Long courseId, Long roomId);
}
