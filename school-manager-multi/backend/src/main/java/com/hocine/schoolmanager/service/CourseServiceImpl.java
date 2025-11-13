package com.hocine.schoolmanager.service;

import com.hocine.schoolmanager.model.Course;
import com.hocine.schoolmanager.model.Room;
import com.hocine.schoolmanager.model.Teacher;
import com.hocine.schoolmanager.repository.CourseRepository;
import com.hocine.schoolmanager.repository.RoomRepository;
import com.hocine.schoolmanager.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repo;
    private final TeacherRepository teacherRepo;
    private final RoomRepository roomRepo;

    public CourseServiceImpl(CourseRepository repo, TeacherRepository teacherRepo, RoomRepository roomRepo) {
        this.repo = repo;
        this.teacherRepo = teacherRepo;
        this.roomRepo = roomRepo;
    }

    @Override public List<Course> findAll() { return repo.findAll(); }
    @Override public Optional<Course> findById(Long id) { return repo.findById(id); }
    @Override public Course save(Course c) { return repo.save(c); }
    @Override public void delete(Long id) { repo.deleteById(id); }
    @Override public Course assignTeacher(Long courseId, Long teacherId) {
        Course course = repo.findById(courseId).orElseThrow();
        Teacher t = teacherRepo.findById(teacherId).orElseThrow();
        course.setTeacher(t);
        return repo.save(course);
    }
    @Override public Course assignRoom(Long courseId, Long roomId) {
        Course course = repo.findById(courseId).orElseThrow();
        Room r = roomRepo.findById(roomId).orElseThrow();
        course.setRoom(r);
        return repo.save(course);
    }
}
