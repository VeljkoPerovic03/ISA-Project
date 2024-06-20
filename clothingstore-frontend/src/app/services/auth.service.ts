import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { User } from '../models/user.model';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080';
  private jwtHelper = new JwtHelperService();
  private currentUserSubject: BehaviorSubject<User | null>;
  public currentUser: Observable<User | null>;

  constructor(
    private http: HttpClient,
    private router: Router,
    private cookieService: CookieService
  ) {
    this.currentUserSubject = new BehaviorSubject<User | null>(null);
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User | null {
    return this.currentUserSubject.value;
  }

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/api/users/register`, user);
  }

  login(credentials: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/api/users/login`, credentials).pipe(
      tap(response => {
        if (response.token) {
          this.cookieService.set('token', response.token);
          this.currentUserSubject.next(response.user); 
          this.router.navigate(['/home']);
        }
      })
    );
  }

  logout() {
    this.cookieService.delete('token');
    this.currentUserSubject.next(null);
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    const token = this.cookieService.get('token');
    return token ? !this.jwtHelper.isTokenExpired(token) : false;
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
