import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Student } from '../models/student.model';
import { StudentService } from '../services/student';

@Component({
  selector: 'app-students',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './students.html'
})
export class Students implements OnInit {
  students: Student[] = [];
  student: Student = { firstName: '', lastName: '', email: '' };
  isEditing = false;

  constructor(private studentService: StudentService) {}

  ngOnInit() {
    this.loadStudents();
  }

  loadStudents() {
    this.studentService.getStudents().subscribe({
      next: (data) => {
        this.students = data;
      },
      error: (error) => {
        console.error('Error loading students:', error);
        alert('Erreur lors du chargement des étudiants');
      }
    });
  }

  saveStudent() {
    if (this.isEditing && this.student.id) {
      this.studentService.updateStudent(this.student.id, this.student).subscribe({
        next: () => {
          this.loadStudents();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error updating student:', error);
          alert('Erreur lors de la modification de l\'étudiant');
        }
      });
    } else {
      this.studentService.createStudent(this.student).subscribe({
        next: () => {
          this.loadStudents();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error creating student:', error);
          alert('Erreur lors de la création de l\'étudiant');
        }
      });
    }
  }

  editStudent(student: Student) {
    this.student = { ...student };
    this.isEditing = true;
  }

  deleteStudent(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer cet étudiant?')) {
      this.studentService.deleteStudent(id).subscribe({
        next: () => {
          this.loadStudents();
        },
        error: (error) => {
          console.error('Error deleting student:', error);
          alert('Erreur lors de la suppression de l\'étudiant');
        }
      });
    }
  }

  resetForm() {
    this.student = { firstName: '', lastName: '', email: '' };
    this.isEditing = false;
  }
}