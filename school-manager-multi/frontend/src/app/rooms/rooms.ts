import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RoomService } from '../services/room';
import { Room } from '../models/room.model';

@Component({
  selector: 'app-rooms',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './rooms.html'
})
export class Rooms implements OnInit {
  rooms: Room[] = [];
  room: Room = { name: '', capacity: 0 };
  isEditing = false;

  constructor(private roomService: RoomService) {}

  ngOnInit() {
    this.loadRooms();
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

  saveRoom() {
    if (this.isEditing && this.room.id) {
      this.roomService.updateRoom(this.room.id, this.room).subscribe({
        next: () => {
          this.loadRooms();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error updating room:', error);
          alert('Erreur lors de la modification de la salle');
        }
      });
    } else {
      this.roomService.createRoom(this.room).subscribe({
        next: () => {
          this.loadRooms();
          this.resetForm();
        },
        error: (error) => {
          console.error('Error creating room:', error);
          alert('Erreur lors de la création de la salle');
        }
      });
    }
  }

  editRoom(room: Room) {
    this.room = { ...room };
    this.isEditing = true;
  }

  deleteRoom(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette salle?')) {
      this.roomService.deleteRoom(id).subscribe({
        next: () => {
          this.loadRooms();
        },
        error: (error) => {
          console.error('Error deleting room:', error);
          alert('Erreur lors de la suppression de la salle');
        }
      });
    }
  }

  resetForm() {
    this.room = { name: '', capacity: 0 };
    this.isEditing = false;
  }
}