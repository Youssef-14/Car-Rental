import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { StorageService } from '../services/storage/storage.service';
import { AuthService } from '../services/auth/auth.service';
import {jwtDecode} from "jwt-decode";
import {catchError, of} from "rxjs";
import { NzMessageService } from 'ng-zorro-antd/message';
import {CustomerService} from '../../../modules/customer/services/customer.service';

export interface MyJwtPayload {
  role: string;
  id: number;
  sub: string;
  iat: number;
  exp: number;
}

@Component({
  selector: 'nz-demo-form-normal-login',
  imports: [ReactiveFormsModule, NzButtonModule, NzCheckboxModule, NzFormModule, NzInputModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {
  isSpinning: boolean = false
  validateForm!: FormGroup

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private customerService: CustomerService,
    private message: NzMessageService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      email: this.fb.control('', [Validators.email, Validators.required]),
      password: this.fb.control('', [Validators.required])
    });
  }

  submitForm(): void {
    console.log(this.validateForm.value);

    this.isSpinning = true; // Activer le spinner
    if (this.validateForm.valid) {
      this.authService.login(this.validateForm.value).pipe(
        catchError((error) => {
          // if error is 401
          if (error.status === 401) {
            this.isSpinning = false; // Désactiver le spinner en cas d'erreur
            this.message.error('Identifiants incorrects', {
              nzDuration: 4000
            });
            return of(null); // Retourner un observable vide pour éviter des erreurs supplémentaires
          }else{
            this.isSpinning = false; // Désactiver le spinner en cas d'erreur
            this.message.error('Une erreur est survenue lors de la connexion', {
              nzDuration: 4000
            });
          }
          return of(null); // Retourner un observable vide pour éviter des erreurs supplémentaires
        })
    ).subscribe(res => {
      this.isSpinning = false; // Désactiver le spinner après la réponse
      if (!res) return; // Arrêter si une erreur a été capturée

      const decodedToken: MyJwtPayload = jwtDecode(res.access_token);
      const { role, id } = decodedToken;

      console.log(decodedToken);

      if (id != null) {
        const user = { id: id, role: role };

        StorageService.saveUser(user);
        StorageService.saveToken(res.access_token);

        if(StorageService.isCustomerLoggedIn()) {
          this.customerService.getProfile(id).subscribe({
            next: (profile) => {
              StorageService.saveProfile(profile);
            },
            error: (error) => {
              console.error('Error fetching profile:', error);
            }
          })
        }

        if (StorageService.isAdminLoggedIn()) {
          this.router.navigateByUrl('/admin/dashboard');
          return;
        }

        if (StorageService.isCustomerLoggedIn()) {
          this.router.navigateByUrl('/customer/dashboard');
          return;
        }

        if (StorageService.isAgentLoggedIn()) {
          this.router.navigateByUrl('/customer/dashboard');
          return;
        }
      }else{
        this.message.error('Identifiants incorrects', {
          nzDuration: 3000
        });
      }
    });
    } else {
      Object.values(this.validateForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }
}
