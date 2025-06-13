import {Component, Input} from '@angular/core'
import { CustomerService } from '../../services/customer.service'
import {NzMessageService} from 'ng-zorro-antd/message';

@Component({
    selector: 'app-my-bookings',
    templateUrl: './my-bookings.component.html',
    styleUrl: './my-bookings.component.scss',
    standalone: false,
})
export class MyBookingsComponent {
  constructor(private service: CustomerService, private message: NzMessageService) {}


  @Input() car: any;

  licenseImage: string | null = null;
  carImage: string | null = null;

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

  visibleCar = false;

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

  closeCar(): void {
    this.visibleCar = false;
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

    this.message.success('Réservation annulée avec succès', {
      nzDuration: 4000
    });
  }

  addReclamation(bookingId: number, description: string) {
    if (!description || description.trim() === '') {
      this.message.error('Veuillez entrer une description pour la réclamation', {
        nzDuration: 4000
      });
      return;
    }

    const reclamation = {
      bookACarId: bookingId,
      description: description
    };

    console.log('Ajout de la réclamation:', reclamation);

    this.service.addReclamation(reclamation).subscribe(
      response => {
        console.log('Réclamation ajoutée avec succès:', response);
        this.closeCar();
      },
      error => {
        console.error('Erreur lors de l\'ajout de la réclamation:', error);
      }
    );

    this.message.success('Réclamation ajoutée avec succès', {
      nzDuration: 4000
    });
  }

  selectedBookingId: number | null = null;
  reclamationDescription: string = '';
  isModalVisible: boolean = false;

  openPopUpReclamation(bookingId: number) {
    this.selectedBookingId = bookingId;
    this.reclamationDescription = '';
    this.isModalVisible = true;
  }

  handleCancel(): void {
    this.isModalVisible = false;
  }

  handleOk(): void {
    if (this.selectedBookingId !== null) {
      this.addReclamation(this.selectedBookingId, this.reclamationDescription);
    }
    this.isModalVisible = false;
  }

}
