// home.component.ts

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // Import CommonModule for NgIf
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule], // Include CommonModule here
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(public authService: AuthService) {}

  ngOnInit(): void {}

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }
}
