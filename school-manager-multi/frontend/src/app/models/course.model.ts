
import { Room } from "./room.model";
import { Teacher } from "./teacher.model";

export interface Course {
  id?: number;
  title: string;
  description: string;
  teacherId: number | null;
  roomId: number | null;
  teacher?: Teacher;
  room?: Room;
}