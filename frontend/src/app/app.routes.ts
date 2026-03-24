import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth-guard';
import { AuthLayout } from './features/auth/auth-layout/auth-layout';
import { Login } from './features/auth/login/login';
import { Signup } from './features/auth/signup/signup';
import { ExerciseList } from './features/exercise/exercise-list/exercise-list';
import { WorkoutDetail } from './features/workout/workout-detail/workout-detail';
import { WorkoutList } from './features/workout/workout-list/workout-list';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'workouts',
    pathMatch: 'full',
  },
  {
    path: 'auth',
    component: AuthLayout,
    children: [
      {
        path: 'login',
        component: Login,
      },
      {
        path: 'signup',
        component: Signup,
      },
    ],
  },
  {
    path: 'workouts',
    component: WorkoutList,
    canActivate: [authGuard],
    canActivateChild: [authGuard],
    children: [
      {
        path: ':id',
        component: WorkoutDetail,
      },
    ],
  },
  {
    path: 'exercises',
    component: ExerciseList,
    canActivate: [authGuard],
  },
  {
    path: '**',
    redirectTo: 'workouts',
    pathMatch: 'full',
  },
];
