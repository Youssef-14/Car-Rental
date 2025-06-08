import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {CustomerService} from '../../services/customer.service';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzInputDirective} from 'ng-zorro-antd/input';
import {Router} from '@angular/router';
import {StorageService} from '../../../../auth/components/services/storage/storage.service';

@Component({
  selector: 'app-verify-mail',
  imports: [
    ReactiveFormsModule,
    NzButtonComponent,
    NzInputDirective
  ],
  templateUrl: './verify-mail.component.html',
  styleUrl: './verify-mail.component.scss'
})
export class VerifyMailComponent {
  verifyMailForm: FormGroup ;

  constructor(private fb: FormBuilder,private customerService: CustomerService, private message: NzMessageService, private router: Router) {
    this.verifyMailForm = this.fb.group({
      code: ['', Validators.required],
    });
  }


  // Method to handle form submission or verification logic can be added here
  onVerifyEmail(): void {

    this.customerService.verifyUserActivationToken(this.verifyMailForm.value.code).subscribe({
      next: (response) => {
        console.log('Email verification sent successfully', response);

        StorageService.verifyUserActivationToken();

        // Optionally show a user-facing toast/snackbar
        this.message.success('L\'adresse e-mail a été vérifiée avec succès. Vous pouvez maintenant vous connecter.');

        // ✅ Redirect only after the response is received
        this.router.navigate(['/customer/dashboard']);
      },
        // Optionally, you can redirect the user or perform any other action after successful verification
        // For example, redirect to the login page or home page
        // redirect to verify email page

      error: (error) => {
        console.error('Error verifying email', error);
        this.message.error('Échec de la vérification de l\'adresse e-mail. Veuillez réessayer.');
      }
    }
    );
  }

  // Additional methods for handling form state, validation, etc. can be added here
  // For example, you might want to add methods to handle form submission, reset, etc.
  // This is a placeholder for any additional functionality you might want to implement


  onResendEmail() {
    this.customerService.generateVerificationCode().subscribe({
      next: (response) => {
        console.log('Email verification sent successfully', response);

        this.message.success('Le code de vérification a été envoyé à votre adresse e-mail. Veuillez vérifier votre boîte de réception.');
      },
      error: (error) => {
        console.error('Error sending email verification', error);
      }
    });
  }

  onBack() {
    // Redirect to the login page or any other page as needed
    this.router.navigate(['/customer/profile']);

  }
}
