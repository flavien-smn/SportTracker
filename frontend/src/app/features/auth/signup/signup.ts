import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Button } from 'primeng/button';
import { Checkbox } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-signup',
  imports: [ReactiveFormsModule, Button, InputTextModule, Checkbox],
  templateUrl: './signup.html',
  styleUrl: './signup.scss',
})
export class Signup {
  signUpForm = new FormGroup({
    email: new FormControl<string>('', [Validators.required, Validators.email]),
    password: new FormControl<string>('', [Validators.required]),
  });

  constructor(private authService: AuthService) {}

  onSubmitSignupForm() {
    console.log('oui');
    if (!this.signUpForm.valid) {
      return;
    }

    this.authService
      .signup({
        email: this.signUpForm.value.email!,
        password: this.signUpForm.value.password!,
      })
      .subscribe({
        next: () => {},
        error: () => {
          console.log('error signup');
        },
      });
  }
}
