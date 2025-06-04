import {Component, OnInit} from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { AdminService } from '../../services/admin.service'
import { NzMessageService } from 'ng-zorro-antd/message'
import { Router } from '@angular/router'

@Component({
    selector: 'app-post-car',
    templateUrl: './post-car.component.html',
    styleUrl: './post-car.component.scss',
    standalone: false
})
export class PostCarComponent implements OnInit{
  postCarForm!: FormGroup
  isSpinning: boolean = false
  selectedFile: File | null = null
  imagePreview: string | ArrayBuffer | null = null
  listOfOption: Array<{ label: string; value: string }> = []
  listOfBrands = ['Toyota', 'Honda', 'BMW', 'Mercedes', 'Audi', 'Lexus', 'Lamborghini', 'Porche', 'Ford', 'Fiat', 'Other']
  listOfType = ['Hyper Car','Sports Car','Luxury Car', 'Suv', 'Sedan','Diesel', 'Crossover']
  listOfColor = ['Rouge', 'Bleu', 'Vert', 'Jaune', 'Orange', 'Violet', 'Noir', 'Blanc', 'Gris', 'Autre']
  listOfTransmission = ['Manual', 'Automatique', 'Semi-automatique']

  constructor(
    private fb: FormBuilder,
    private adminService: AdminService,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.postCarForm = this.fb.group({
      name: [null, Validators.required],
      brand: [null, Validators.required],
      type: [null, Validators.required],
      color: [null, Validators.required],
      transmission: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
      year: [null, Validators.required]
    })
  }

  postCar() {
    this.isSpinning = true

    // Valider chaque champ du formulaire

    for (const i in this.postCarForm.controls) {
      this.postCarForm.controls[i].markAsDirty()
      this.postCarForm.controls[i].updateValueAndValidity()
    }

    if (this.postCarForm.invalid || !this.selectedFile) {
      this.isSpinning = false
      this.message.error('Sil vous plaît, remplissez tous les champs du formulaire et sélectionnez une image.', { nzDuration: 3000 })
      return
    }

    // validation du year , price , nom , description

    if (this.postCarForm.value.year < 1900 || this.postCarForm.value.year > new Date().getFullYear()) {
      this.isSpinning = false
      this.message.error('L\'année doit être entre 1900 et l\'année en cours.', { nzDuration: 3000 })
      return
    }

    if (this.postCarForm.value.price <= 0) {
      this.isSpinning = false
      this.message.error('Le prix doit être supérieur à 0.', { nzDuration: 3000 })
      return
    }

    if (this.postCarForm.value.name.trim() === '') {
      this.isSpinning = false
      this.message.error('Le nom de la voiture ne peut pas être vide.', { nzDuration: 3000 })
      return
    }

    if (this.postCarForm.value.description.trim() === '') {
      this.isSpinning = false
      this.message.error('La description de la voiture ne peut pas être vide.', { nzDuration: 3000 })
      return
    }


    const formData: FormData = new FormData()
    formData.append('image', this.selectedFile as Blob)
    formData.append('brand', this.postCarForm.value.brand)
    formData.append('name', this.postCarForm.value.name)
    formData.append('type', this.postCarForm.value.type)
    formData.append('color', this.postCarForm.value.color)
    formData.append('year', this.postCarForm.value.year)
    formData.append('transmission', this.postCarForm.value.transmission)
    formData.append('description', this.postCarForm.value.description)
    formData.append('price', this.postCarForm.value.price)

    this.adminService.postCar(formData).subscribe(
      res => {
        this.message.success('Car posted successfully', { nzDuration: 3000 })
        this.isSpinning = false
        this.router.navigateByUrl('/admin/dashboard')
      },
      error => {
        this.message.error('Error posting car', { nzDuration: 3000 })
        console.log(error)
      }
    )
  }

  onFileSelected($event: Event) {
    const target = $event.target as HTMLInputElement
    this.selectedFile = (target.files as FileList)[0]

    this.previewImage()
  }

  previewImage() {
    const reader = new FileReader()
    reader.onload = () => {
      this.imagePreview = reader.result
    }
    reader.readAsDataURL(this.selectedFile as Blob)
  }
}
