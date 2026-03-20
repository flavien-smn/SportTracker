import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode, JwtPayload } from 'jwt-decode';
import { Observable, tap } from 'rxjs';
import { AuthResponse, SigninRequest, SignupRequest } from '../shared/models/auth.model';

@Injectable({ providedIn: 'root' })
export class AuthService {
  constructor(private http: HttpClient) {}

  login(signinRequest: SigninRequest): Observable<AuthResponse> {
    return this.http
      .post<{ token: string }>('/api/auth/signin', signinRequest)
      .pipe(tap((response) => this.setToken(response.token)));
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  signup(signupRequest: SignupRequest): Observable<void> {
    return this.http.post<void>('/api/auth/signup', signupRequest);
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
