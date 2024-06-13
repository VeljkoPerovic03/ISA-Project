import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080';
  private jwtHelper = new JwtHelperService();

  constructor(
    private http: HttpClient,
    private router: Router,
    private cookieService: CookieService
  ) {}

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/public/users/register`, user);
  }

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/public/users/login`, credentials);
  }

  handleLoginResponse(response: any) {
    this.cookieService.set('token', response.token);
    this.router.navigate(['/home']);
  }

  logout() {
    this.cookieService.delete('token');
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    const token = this.cookieService.get('token');
    return !this.jwtHelper.isTokenExpired(token);
  }

  getRole(): string {
    const token = this.cookieService.get('token');
    const decodedToken = this.jwtHelper.decodeToken(token);
    return decodedToken ? decodedToken.roles : 'No role assigned';
  }
  
  isAdmin(): boolean {
    return this.getRole().includes('ROLE_ADMIN');
  }  
}
