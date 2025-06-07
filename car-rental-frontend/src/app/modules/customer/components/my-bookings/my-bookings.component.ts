import { Component } from '@angular/core'
import { CustomerService } from '../../services/customer.service'

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

  visible = false;

  open(): void {
    this.visible = true;
  }

  close(): void {
    this.visible = false;
  }

  isCancelable(booking: any): boolean {
    const today = new Date();
    const fromDate = new Date(booking.fromDate);

    // Calculate the difference in days
    const diffTime = fromDate.getTime() - today.getTime();
    const diffDays = diffTime / (1000 * 60 * 60 * 24);

    return (
      booking.bookCarStatus === 'PENDING'
      //|| (booking.bookCarStatus === 'APPROVED' && diffDays >= 2)
    );
  }


  cancelBooking(id:any) {

    // message to confirm cancellation
    if (!confirm('Voulez-vous vraiment annuler cette réservation ?')) {
      return;
    }
    this.isSpinning = true;

    this.service.cancelBooking(id).subscribe(
      data => {
        console.log(data);
        this.getBookingsByUserId();
        this.isSpinning = false;

        // update the booking status in the UI
        const bookingIndex = this.bookings.findIndex(booking => booking.id === id);
        if (bookingIndex !== -1) {
          this.bookings[bookingIndex].bookCarStatus = 'CANCELED';
        }
      },
      error => {
        console.log(error);
        this.isSpinning = false;
      }
    );
  }
}
