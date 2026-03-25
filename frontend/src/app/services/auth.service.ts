import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode, JwtPayload } from 'jwt-decode';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { AuthResponse, SigninRequest, SignupRequest } from '../shared/models/auth.model';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
    const token = this.getToken();
    if (token && this.isExpired(token)) {
      this.logout();
    }
  }

  login(signinRequest: SigninRequest): Observable<AuthResponse> {
    return this.http
      .post<{ token: string }>(`${this.apiUrl}/auth/signin`, signinRequest)
      .pipe(tap((response) => this.setToken(response.token)));
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  signup(signupRequest: SignupRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/auth/signup`, signupRequest);
  }

  private setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    if (token) {
      return !this.isExpired(token);
    }
    return false;
  }

  isExpired(token: string): boolean {
    try {
      const decodedToken: JwtPayload = jwtDecode(token);
      return decodedToken.exp ? decodedToken.exp < Date.now() / 1000 : true;
    } catch {
      return true;
    }
  }
}
