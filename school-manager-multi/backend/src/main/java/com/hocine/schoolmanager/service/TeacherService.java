package com.hocine.schoolmanager.service;

import com.hocine.schoolmanager.model.Teacher;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Teacher save(Teacher t);
    void delete(Long id);
}
