package com.hocine.schoolmanager.service;

import com.hocine.schoolmanager.model.Teacher;
import com.hocine.schoolmanager.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repo;
    public TeacherServiceImpl(TeacherRepository repo) { this.repo = repo; }
    @Override public List<Teacher> findAll() { return repo.findAll(); }
    @Override public Optional<Teacher> findById(Long id) { return repo.findById(id); }
    @Override public Teacher save(Teacher t) { return repo.save(t); }
    @Override public void delete(Long id) { repo.deleteById(id); }
}
