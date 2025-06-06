import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {StorageService} from '../../../../auth/components/services/storage/storage.service';
import {NgIf} from '@angular/common';
import {CustomerService} from '../../services/customer.service';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {NzWaveDirective} from 'ng-zorro-antd/core/wave';

@Component({
  selector: 'app-my-profile',
  imports: [
    ReactiveFormsModule,
    NgIf,
    NzButtonComponent,
    NzWaveDirective

  ],
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.scss'
})
export class MyProfileComponent implements OnInit {
  profileForm: FormGroup;
  driverLicenseImageUrl: string | null = null;
  selectedFile: File | null = null;

  constructor(private fb: FormBuilder,private customerService: CustomerService,) {
    this.profileForm = this.fb.group({
      lastname: ['', Validators.required],
      firstname: ['', Validators.required],
      number: ['', [Validators.required, Validators.pattern('^[0-9]{10,15}$')]],
      licenseNumber: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    const profile = StorageService.getProfile();
    if (profile) {
      this.profileForm.patchValue({
        lastname: profile.lastname,
        firstname: profile.firstname,
        number: profile.number,
        licenseNumber: profile.licenseNumber,
      });

      if (profile.licenseImage) {
        this.driverLicenseImageUrl = profile.licenseImage.startsWith('data:image')
          ? profile.licenseImage
          : `data:image/jpeg;base64,${profile.licenseImage}`;

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
        this.driverLicenseImageUrl = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  getDriverLicenseImageUrl(): string | null {
    console.log('Driver License Image URL:', this.driverLicenseImageUrl);
    return this.driverLicenseImageUrl
      ? `data:image/jpeg;base64,${this.driverLicenseImageUrl}`
      : null;
  }

  onSubmit(): void {
    const formData : FormData = new FormData()

    formData.append('id', StorageService.getUserId() || '0');
    if (this.selectedFile) {
      formData.append('licenseImage', this.selectedFile);
    }

    formData.append('lastName', this.profileForm.value.lastname);
    formData.append('firstName', this.profileForm.value.firstname);
    formData.append('phoneNumber', this.profileForm.value.number);
    formData.append('licenseNumber', this.profileForm.value.licenseNumber);


    StorageService.saveProfile({
      lastname: this.profileForm.value.lastname,
      firstname: this.profileForm.value.firstname,
      number: this.profileForm.value.number,
      licenseNumber: this.profileForm.value.licenseNumber,
      driverLicenseImageBase64: this.driverLicenseImageUrl ? this.driverLicenseImageUrl.split(',')[1] : null
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
}
