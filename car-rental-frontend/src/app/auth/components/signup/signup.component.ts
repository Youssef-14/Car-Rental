import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { AuthService } from '../services/auth/auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';


@Component({
  selector: 'nz-demo-form-normal-signup',
  imports: [ReactiveFormsModule, NzButtonModule, NzCheckboxModule, NzFormModule, NzInputModule, RouterLink],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent implements OnInit {
  isSpinning: boolean = false
  validateForm!: FormGroup

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private message: NzMessageService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: this.fb.control('', [Validators.required]),
      email: this.fb.control('', [Validators.email, Validators.required]),
      password: this.fb.control('', [Validators.required]),
      confirmPassword: this.fb.control('', [Validators.required])
    });
  }

  submitForm(): void {
    console.log(this.validateForm.value)
    this.authService.customerRegister(this.validateForm.value).subscribe(
      res => {
        console.log('res', res)

        if (res.success) {
          this.message.success('Utilisateur créé avec succès', {
            nzDuration: 5000
          })
          this.router.navigateByUrl('/login')
        } else {
          this.message.error('Email est déjà utilisé', {
            nzDuration: 5000
          })
        }
      },
      err => {
        console.log(err)
      }
    )
  }
}
