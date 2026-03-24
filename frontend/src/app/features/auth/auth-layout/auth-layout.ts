import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LucideDynamicIcon } from '@lucide/angular';
import { CardModule } from 'primeng/card';
import { ChipModule } from 'primeng/chip';

@Component({
  selector: 'app-auth-layout',
  imports: [RouterOutlet, CardModule, ChipModule, LucideDynamicIcon],
  templateUrl: './auth-layout.html',
  styleUrl: './auth-layout.scss',
})
export class AuthLayout {}
