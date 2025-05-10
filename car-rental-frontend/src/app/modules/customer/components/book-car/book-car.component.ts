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


    this.carId = this.activeRoute.snapshot.params['id']
    this.getCarById()
  }

  private getCarById() {
    this.service.getCarById(this.carId).subscribe(res => {
      this.car = res
      this.car.processedImage = `data:image/jpeg;base64,${res.returnedImage}`
    })
  }

  onSubmit(){
    this.isSpinning = true

    let bookACarDto = {
      fromDate: this.date_from,
      toDate: this.date_to,
      days: this.days,
      userId: StorageService.getUserId(),
      carId: this.carId
    }

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
