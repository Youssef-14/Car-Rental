import {Component, Input, OnInit} from '@angular/core';
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
import {NzDescriptionsComponent, NzDescriptionsItemComponent} from 'ng-zorro-antd/descriptions';
import {NzDividerComponent} from 'ng-zorro-antd/divider';
import {NzDrawerComponent, NzDrawerContentDirective} from 'ng-zorro-antd/drawer';
import {TextTranslator} from '../../../../shared/utils/text-translator';

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
    NzWaveDirective,
    NzDrawerComponent,
    NzDrawerContentDirective
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

  @Input() profile: any;

  licenseImage: string | null = null;

  carName: string = '';
  carBrand: string = '';

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

        this.message.success('La réservation a été mise à jour avec succès')
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
      this.carBrand = this.bookings[0]?.car?.brand || '';
      this.carName = this.bookings[0]?.car?.name || '';
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

  protected readonly TextTranslator = TextTranslator;
}
