package com.hocine.schoolmanager.service;

import com.hocine.schoolmanager.model.Course;
import com.hocine.schoolmanager.model.Student;
import com.hocine.schoolmanager.repository.CourseRepository;
import com.hocine.schoolmanager.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> findAll() { return studentRepository.findAll(); }

    @Override
    public Optional<Student> findById(Long id) { return studentRepository.findById(id); }

    @Override
    public Student save(Student s) { return studentRepository.save(s); }

    @Override
    public void delete(Long id) { studentRepository.deleteById(id); }

    @Override
    @Transactional
    public Student enrollInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        student.getCourses().add(course);
        course.getStudents().add(student);
        courseRepository.save(course);
        return studentRepository.save(student);
    }
}
