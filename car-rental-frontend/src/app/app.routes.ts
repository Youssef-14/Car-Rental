import { Routes } from '@angular/router';
import { SignupComponent } from './auth/components/signup/signup.component'
import { LoginComponent } from './auth/components/login/login.component'
import { adminGuard } from './guards/authorization/admin.guard';
import { customerGuard } from './guards/authorization/customer.guard';
import { guestGuard } from './guards/auth/guest.guard';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'register',
    canActivate: [guestGuard],
    component: SignupComponent
  },
  {
    path: 'login',
    canActivate: [guestGuard],
    component: LoginComponent
  },
  {
    path: 'admin',
    canActivate: [adminGuard],
    loadChildren: () =>
      import('./modules/admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'customer',
    canActivate: [customerGuard],
    loadChildren: () =>
      import('./modules/customer/customer.module').then(m => m.CustomerModule)
  }
];
