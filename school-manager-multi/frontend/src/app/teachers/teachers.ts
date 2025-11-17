import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TeacherService } from '../services/teacher';
import { Teacher } from '../models/teacher.model';

@Component({
  selector: 'app-teachers',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './teachers.html'
})
export class Teachers implements OnInit {
  teachers: Teacher[] = [];
  teacher: Teacher = { firstName: '', lastName: '', email: '' };
  isEditing = false;

  constructor(private teacherService: TeacherService) {}

  ngOnInit() {
    this.loadTeachers();
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

  saveTeacher() {
    if (this.isEditing && this.teacher.id) {
      this.teacherService.updateTeacher(this.teacher.id, this.teacher).subscribe({
        next: () => {
          this.loadTeachers();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error updating teacher:', error);
          alert('Erreur lors de la modification du professeur');
        }
      });
    } else {
      this.teacherService.createTeacher(this.teacher).subscribe({
        next: () => {
          this.loadTeachers();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error creating teacher:', error);
          alert('Erreur lors de la création du professeur');
        }
      });
    }
  }

  editTeacher(teacher: Teacher) {
    this.teacher = { ...teacher };
    this.isEditing = true;
  }

  deleteTeacher(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce professeur?')) {
      this.teacherService.deleteTeacher(id).subscribe({
        next: () => {
          this.loadTeachers();
        },
        error: (error) => {
          console.error('Error deleting teacher:', error);
          alert('Erreur lors de la suppression du professeur');
        }
      });
    }
  }

  resetForm() {
    this.teacher = { firstName: '', lastName: '', email: '' };
    this.isEditing = false;
  }
}