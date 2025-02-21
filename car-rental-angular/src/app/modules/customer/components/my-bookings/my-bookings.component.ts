import { Component } from '@angular/core'
import { CustomerService } from '../../services/customer.service'
import { ActivatedRoute } from '@angular/router'
import { StorageService } from '../../../../auth/components/services/storage/storage.service'

@Component({
    selector: 'app-my-bookings',
    templateUrl: './my-bookings.component.html',
    styleUrl: './my-bookings.component.scss',
    standalone: false
})
export class MyBookingsComponent {
  constructor(private service: CustomerService) {}

  bookings: any[] = []
  isSpinning = false

  ngOnInit() {
    this.getBookingsByUserId()
  }

  private getBookingsByUserId() {
    this.isSpinning = true

    this.service.getBookingsByUserId().subscribe(
      data => {
        this.bookings = data
        console.log(this.bookings);
        this.isSpinning = false
      },
      error => {
        console.log(error)
        this.isSpinning = false
      }
    )
  }
}
