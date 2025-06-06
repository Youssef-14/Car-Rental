import {Component, OnInit} from '@angular/core'
import { AdminService } from '../../services/admin.service'
import { NzMessageService } from 'ng-zorro-antd/message'
import {Booking} from '../../../../models/booking';

@Component({
    selector: 'app-get-bookings',
    templateUrl: './get-bookings.component.html',
    styleUrl: './get-bookings.component.scss',
    standalone: false
})
export class GetBookingsComponent implements OnInit {
  constructor(
    private adminService: AdminService,
    private message: NzMessageService
  ) {}

  bookings: Booking[] = []
  isSpinning = false

  ngOnInit() {
    this.getBookings()
  }

  changeBookingStatus(bookingId: number, status: string) {

    // message confirmation before changing status
    if (!window.confirm(`Voulez-vous vraiment changer le statut de la réservation ${bookingId} à ${status} ?`)) {
      return
    }

    this.adminService.changeBookingStatus(bookingId, status).subscribe(
      () => {
        this.getBookings()

        this.message.success('Booking status changed successfully')
      },
      error => {
        this.message.error('Error changing booking status')
      }
    )
  }

  private getBookings() {
    this.isSpinning = true

    this.adminService.getCarBookingsByStatus("PENDING").subscribe(bookings => {
      this.bookings = bookings
      console.log(bookings);
      this.isSpinning = false
    })
  }

  visible = false;

  openUser(): void {
    this.visible = true;
  }

  closeUser(): void {
    this.visible = false;
  }
}
