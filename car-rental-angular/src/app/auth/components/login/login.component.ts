import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { AuthService } from '../services/auth/auth.service'
import { StorageService } from '../services/storage/storage.service'
import { Router } from '@angular/router'
import { NzMessageService } from 'ng-zorro-antd/message'
import {jwtDecode} from "jwt-decode";


export interface MyJwtPayload {
  role: string;
  id: number;
  sub: string;
  iat: number;
  exp: number;
}

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss',
    standalone: false
})
export class LoginComponent implements OnInit {
  isSpinning: boolean = false
  loginForm!: FormGroup

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private message: NzMessageService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]]
    })
  }

  login() {
    this.authService.login(this.loginForm.value).subscribe(res => {

      const decodedToken: MyJwtPayload  = jwtDecode(res.access_token)
      const { role, id, sub, iat, exp } = decodedToken;


      console.log(decodedToken)

      if (id != null) {
        const user = {
          id: id,
          role: role
        }

        StorageService.saveUser(user)
        StorageService.saveToken(res.access_token)

        if (StorageService.isAdminLoggedIn()) {
          this.router.navigateByUrl('/admin/dashboard')
          return
        }

        if (StorageService.isCustomerLoggedIn()) {
          this.router.navigateByUrl('/customer/dashboard')
          return
        }

        if (StorageService.isAgentLoggedIn()) {
          this.router.navigateByUrl('/customer/dashboard')
          return
        }

        this.message.error('Bad credentials', {
          nzDuration: 3000
        })
      }
    })
  }
}
