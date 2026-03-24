import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Button } from 'primeng/button';
import { Checkbox } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, Button, InputTextModule, Checkbox, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  signInForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(private authService: AuthService) {}

  onSubmitLoginForm() {
    console.log('oui');
    if (!this.signInForm.valid) {
      return;
    }
    const { email, password } = this.signInForm.value;

    this.authService.login({ email: email!, password: password! }).subscribe({
      next: (token) => {
        console.log('youhouuuu', token);
      },
      error: (err) => {
        console.error('Erreur login:', err);
      },
    });
  }
}
