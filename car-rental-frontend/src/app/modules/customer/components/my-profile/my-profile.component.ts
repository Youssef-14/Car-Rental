import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {StorageService} from '../../../../auth/components/services/storage/storage.service';
import {NgIf} from '@angular/common';
import {CustomerService} from '../../services/customer.service';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {NzWaveDirective} from 'ng-zorro-antd/core/wave';
import {Router} from '@angular/router';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzInputDirective} from 'ng-zorro-antd/input';
import {NzFormControlComponent} from 'ng-zorro-antd/form';
import {NzColDirective} from 'ng-zorro-antd/grid';

@Component({
  selector: 'app-my-profile',
  imports: [
    ReactiveFormsModule,
    NgIf,
    NzButtonComponent,
    NzWaveDirective,
    NzFormControlComponent,
    NzColDirective
  ],
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.scss'
})
export class MyProfileComponent implements OnInit {
  profileForm: FormGroup;
  licenseImage: string | null = null;
  selectedFile: File | null = null;
  profile : any = null;

  constructor(private fb: FormBuilder,private customerService: CustomerService, private router: Router, private message: NzMessageService) {
    this.profileForm = this.fb.group({
      lastname: ['', Validators.required],
      firstname: ['', Validators.required],
      number: ['', Validators.required],
      licenseNumber: ['', Validators.required],
      address: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.profile = StorageService.getProfile();
    if (this.profile) {
      this.profileForm.patchValue({
        lastname: this.profile.lastname,
        firstname: this.profile.firstname,
        number: this.profile.number,
        licenseNumber: this.profile.licenseNumber,
        address: this.profile.address
      });

      console.log('Profile loaded from storage:', this.profile);


      if (this.profile.licenseImage) {
        this.licenseImage = this.profile.licenseImage.startsWith('data:image')
          ? this.profile.licenseImage
          : `data:image/jpeg;base64,${this.profile.licenseImage}`;

      }
    }
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      this.selectedFile = file;

      const reader = new FileReader();
      reader.onload = () => {
        this.licenseImage = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  onSubmit(): void {

    if (this.profileForm.invalid) {
      this.message.error('Veuillez remplir tous les champs correctement.');
      return;
    }


    const formData : FormData = new FormData()

    formData.append('id', StorageService.getUserId() || '0');
    if (this.selectedFile) {
      formData.append('licenseImage', this.selectedFile);
    }

    formData.append('lastName', this.profileForm.value.lastname);
    formData.append('firstName', this.profileForm.value.firstname);
    formData.append('phoneNumber', this.profileForm.value.number);
    formData.append('licenseNumber', this.profileForm.value.licenseNumber);
    formData.append('address', this.profileForm.value.address);


    StorageService.saveProfile({
      lastname: this.profileForm.value.lastname,
      firstname: this.profileForm.value.firstname,
      number: this.profileForm.value.number,
      licenseNumber: this.profileForm.value.licenseNumber,
      address: this.profileForm.value.address,
      licenseImage: this.licenseImage ? this.licenseImage.split(',')[1] : null,
      isVerified: this.profile?.isVerified || false
    });

    this.customerService.updateProfile(formData).subscribe({
      next: (response) => {
        console.log('Profile updated successfully', response);
      },
      error: (error) => {
        console.error('Error updating profile', error);
      }
    });
  }

  VerifyMail() {

    this.customerService.generateVerificationCode().subscribe({
      next: (response) => {
        this.message.success('Le code de vérification a été envoyé à votre adresse e-mail. Veuillez vérifier votre boîte de réception.');
        console.log('Email verification sent successfully', response);
      },
      error: (error) => {
        console.error('Error sending email verification', error);
      }
    });

    // redirect to verify email page
    this.router.navigate(['/customer/verify-email']);
  }
}
