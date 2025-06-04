import {Component, OnInit} from '@angular/core';
import {DatePipe, NgIf} from '@angular/common';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {NzColDirective, NzRowDirective} from 'ng-zorro-antd/grid';
import {NzSpinComponent} from 'ng-zorro-antd/spin';
import {NzTableComponent} from 'ng-zorro-antd/table';
import {NzWaveDirective} from 'ng-zorro-antd/core/wave';
import {AdminService} from '../../services/admin.service';
import {NzMessageService} from 'ng-zorro-antd/message';
import {Booking} from '../../../../models/booking';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-get-car-bookings',
  imports: [
    DatePipe,
    NgIf,
    NzButtonComponent,
    NzColDirective,
    NzRowDirective,
    NzSpinComponent,
    NzTableComponent,
    NzWaveDirective
  ],
  templateUrl: './get-car-bookings.component.html',
  styleUrl: './get-car-bookings.component.scss'
})
export class GetCarBookingsComponent implements OnInit {
  constructor(
    private adminService: AdminService,
    private message: NzMessageService,
    private activatedRoute: ActivatedRoute
  ) {}

  bookings: Booking[] = []
  isSpinning = false
  carId!: number

  ngOnInit() {
    this.carId = this.activatedRoute.snapshot.params['id']
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

    this.adminService.getCarBookingsByCar(this.carId).subscribe(bookings => {
      this.bookings = bookings
      console.log(bookings);
      this.isSpinning = false
    })
  }

}
