import {Component, Input, OnInit} from '@angular/core'
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

  @Input() profile: any;

  licenseImage: string | null = null;

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

        this.message.success('La réservation a été mise à jour avec succès')
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

  openUser(user: any): void {
    this.licenseImage = null;
    this.visible = true;
    this.profile = user;
    if (this.profile?.licenseImage) {
      this.licenseImage = this.profile.licenseImage.startsWith('data:image')
        ? this.profile.licenseImage
        : `data:image/jpeg;base64,${this.profile.licenseImage}`;
    }

  }

  closeUser(): void {
    this.visible = false;
  }
}
