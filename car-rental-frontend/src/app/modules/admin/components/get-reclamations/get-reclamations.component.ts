import {Component, Input} from '@angular/core';
import {DatePipe, NgIf} from '@angular/common';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {NzColDirective, NzRowDirective} from 'ng-zorro-antd/grid';
import {NzDrawerComponent} from 'ng-zorro-antd/drawer';
import {NzSpinComponent} from 'ng-zorro-antd/spin';
import {NzTableComponent} from 'ng-zorro-antd/table';
import {NzWaveDirective} from 'ng-zorro-antd/core/wave';
import {AdminService} from '../../services/admin.service';
import {NzMessageService} from 'ng-zorro-antd/message';

@Component({
  selector: 'app-get-reclamations',
  imports: [
    DatePipe,
    NgIf,
    NzButtonComponent,
    NzColDirective,
    NzDrawerComponent,
    NzRowDirective,
    NzSpinComponent,
    NzTableComponent,
    NzWaveDirective
  ],
  templateUrl: './get-reclamations.component.html',
  styleUrl: './get-reclamations.component.scss'
})
export class GetReclamationsComponent {
  constructor(
    private adminService: AdminService,
    private message: NzMessageService
  ) {}

  @Input() profile: any;

  @Input() car: any;

  licenseImage: string | null = null;
  carImage: string | null = null;

  reclamations: any[] = []
  isSpinning = false

  ngOnInit() {
    this.getReclamations()
  }

  changeBookingStatus(bookingId: number, status: string) {

    // message confirmation before changing status
    if (!window.confirm(`Voulez-vous vraiment changer le statut de la réservation ${bookingId} à ${status} ?`)) {
      return
    }

    this.adminService.changeBookingStatus(bookingId, status).subscribe(
      () => {
        this.getReclamations()

        this.message.success('La réservation a été mise à jour avec succès')
      },
      error => {
        this.message.error('Error changing booking status')
      }
    )
  }

  private getReclamations() {
    this.isSpinning = true

    this.adminService.getReclamations().subscribe(reclamations => {
      this.reclamations = reclamations
      console.log(reclamations);
      this.isSpinning = false
    })
  }

  visible = false;
  visibleCar = false;

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

  openCar(car: any): void {
    this.carImage = null;
    this.visibleCar = true;
    this.car = car;
    if (this.car?.returnedImage) {
      this.carImage = this.car.returnedImage.startsWith('data:image')
        ? this.car.returnedImage
        : `data:image/jpeg;base64,${this.car.returnedImage}`;
    }
  }

  closeUser(): void {
    this.visible = false;
  }

  closeCar(): void {
    this.visibleCar = false;
  }

}
