import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CourseService } from '../services/course';
import { TeacherService } from '../services/teacher';
import { RoomService } from '../services/room';
import { Course } from '../models/course.model';
import { Teacher } from '../models/teacher.model';
import { Room } from '../models/room.model';

@Component({
  selector: 'app-courses',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './courses.html'
})
export class Courses implements OnInit {
  courses: Course[] = [];
  course: Course = { title: '', description: '', teacherId: null, roomId: null };
  teachers: Teacher[] = [];
  rooms: Room[] = [];
  isEditing = false;

  constructor(
    private courseService: CourseService,
    private teacherService: TeacherService,
    private roomService: RoomService
  ) {}

  ngOnInit() {
    this.loadCourses();
    this.loadTeachers();
    this.loadRooms();
  }

  loadCourses() {
    this.courseService.getCourses().subscribe({
      next: (data) => {
        this.courses = data;
      },
      error: (error) => {
        console.error('Error loading courses:', error);
        alert('Erreur lors du chargement des cours');
      }
    });
  }

  loadTeachers() {
    this.teacherService.getTeachers().subscribe({
      next: (data) => {
        this.teachers = data;
      },
      error: (error) => {
        console.error('Error loading teachers:', error);
        alert('Erreur lors du chargement des professeurs');
      }
    });
  }

  loadRooms() {
    this.roomService.getRooms().subscribe({
      next: (data) => {
        this.rooms = data;
      },
      error: (error) => {
        console.error('Error loading rooms:', error);
        alert('Erreur lors du chargement des salles');
      }
    });
  }

  saveCourse() {
    if (this.isEditing && this.course.id) {
      this.courseService.updateCourse(this.course.id, this.course).subscribe({
        next: () => {
          this.loadCourses();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error updating course:', error);
          alert('Erreur lors de la modification du cours');
        }
      });
    } else {
      this.courseService.createCourse(this.course).subscribe({
        next: () => {
          this.loadCourses();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error creating course:', error);
          alert('Erreur lors de la création du cours');
        }
      });
    }
  }

  editCourse(course: Course) {
    this.course = { ...course };
    this.isEditing = true;
  }

  deleteCourse(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce cours?')) {
      this.courseService.deleteCourse(id).subscribe({
        next: () => {
          this.loadCourses();
        },
        error: (error) => {
          console.error('Error deleting course:', error);
          alert('Erreur lors de la suppression du cours');
        }
      });
    }
  }

  resetForm() {
    this.course = { title: '', description: '', teacherId: null, roomId: null };
    this.isEditing = false;
  }
}