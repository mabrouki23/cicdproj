import { Routes } from '@angular/router';

export const routes: Routes = [
	{ path: '', loadComponent: () => import('./home/home').then(m => m.HomeComponent) },
	{ path: 'students', loadComponent: () => import('./students/students').then(m => m.Students) },
	 { path: 'teachers', loadComponent: () => import('./teachers/teachers').then(m => m.Teachers) },
	 { path: 'courses', loadComponent: () => import('./courses/courses').then(m => m.Courses) },
	 { path: 'rooms', loadComponent: () => import('./rooms/rooms').then(m => m.Rooms) },
];
