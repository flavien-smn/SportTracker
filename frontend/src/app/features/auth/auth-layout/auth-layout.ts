import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { LucideDynamicIcon } from '@lucide/angular';
import { Button } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { ChipModule } from 'primeng/chip';
import { DividerModule } from 'primeng/divider';

@Component({
  selector: 'app-auth-layout',
  imports: [RouterOutlet, CardModule, ChipModule, LucideDynamicIcon, DividerModule, Button],
  templateUrl: './auth-layout.html',
  styleUrl: './auth-layout.scss',
})
export class AuthLayout {}
