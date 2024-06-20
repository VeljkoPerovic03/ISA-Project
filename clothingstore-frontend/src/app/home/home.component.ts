import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  text: string = 'Welcome To Velja Clothing Store';
  textArray: string[] = [];

  constructor(public authService: AuthService) {}

  ngOnInit(): void {
    this.textArray = this.text.split(/(?=[ ])|(?<=[ ])/g);
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  getRainbowStyle(char: string) {
    const index = this.textArray.indexOf(char);
    const hue = index * 360 / this.textArray.length;
    return { color: `hsl(${hue}, 100%, 50%)` };
  }
}
