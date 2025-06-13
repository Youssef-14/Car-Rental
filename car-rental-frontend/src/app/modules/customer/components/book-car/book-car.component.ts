import { Component } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'
import { CustomerService } from '../../services/customer.service'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { StorageService } from '../../../../auth/components/services/storage/storage.service'
import { NzMessageService } from 'ng-zorro-antd/message'
import { FormsModule } from '@angular/forms'

const DATE_FORMAT = 'MM-DD-YYYY'

@Component({
    selector: 'app-book-car',
    templateUrl: './book-car.component.html',
    styleUrl: './book-car.component.scss',
    standalone: false
})
export class BookCarComponent {
  constructor(
    private service: CustomerService,
    private activeRoute: ActivatedRoute,
    private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router
  ) {}

  carId!: number
  car: any
  isSpinning: boolean = false
  date_from!:string;
  date_to!: string;
  days!: number;

  ngOnInit() {


    this.carId = Number(this.activeRoute.snapshot.params['id'])

    this.getCarIndiponibleDates(this.carId)
    this.getCarById()
  }

  unavailableDates: string[] = [
  ];

  getCarIndiponibleDates (carId: number) {
    this.service.getCarIndisponibleDates(carId).subscribe(
      (res: string[]) => {
        this.unavailableDates = res;
      },
      (error) => {
        console.error('Error fetching unavailable dates:', error);
      }
    );

  }

  isDateUnavailable = (dateStr: string): boolean => {
    return this.unavailableDates.includes(dateStr);
  };

  disableUnavailableDates = (current: Date): boolean => {
    const formatted = current.toISOString().slice(0, 10); // 'YYYY-MM-DD'
    return this.unavailableDates.includes(formatted);
  };




  private getCarById() {
    this.service.getCarById(this.carId).subscribe(res => {
      this.car = res
      this.car.processedImage = `data:image/jpeg;base64,${res.returnedImage}`
    })
  }

  onSubmit(){

    // Check if the user profile is complete
    const profile = StorageService.getProfile()
    if (!profile || !profile.licenseImage || !profile.licenseNumber || !profile.address || !profile.firstname || !profile.lastname || !profile.number) {
      this.message.error('Veuillez compléter votre profil avant de réserver une voiture')
      this.router.navigate(['/customer/profile'])
      return
    }

    if (!profile.isVerified) {
      this.message.error('Veuillez vérifier votre adresse e-mail avant de réserver une voiture')
      this.router.navigate(['/customer/profile'])
      return
    }

    this.isSpinning = true

    let bookACarDto = {
      fromDate: this.date_from,
      toDate: this.date_to,
      days: this.days,
      userId: StorageService.getUserId(),
      carId: this.carId
    }

    // Validate the form data

    // si fromDate > toDate, afficher un message d'erreur
    if (new Date(this.date_from) > new Date(this.date_to)) {
      this.isSpinning = false
      this.message.error('La date de début ne peut pas être postérieure à la date de fin')
      return
    }

    if (!bookACarDto.fromDate || !bookACarDto.toDate ) {
      this.isSpinning = false
      this.message.error('Remplissez tous les champs requis')
      return
    }

    bookACarDto.days = Math.ceil((new Date(this.date_to).getTime() - new Date(this.date_from).getTime()) / (1000 * 3600 * 24))

    console.log(bookACarDto)

    this.service.bookACar(bookACarDto).subscribe(
      res => {
        this.isSpinning = false

        this.message.success('Car booked successfully')
        this.router.navigateByUrl('/customer/dashboard')
      },

      error => {
        this.isSpinning = false
        this.message.error('Error occurred while booking the car')
      }
    )
  }
}
