import { Component } from '@angular/core'
import {AdminService} from '../../../admin/services/admin.service';
import {CustomerService} from '../../services/customer.service';

@Component({
    selector: 'app-customer-dashboard',
    templateUrl: './customer-dashboard.component.html',
    styleUrl: './customer-dashboard.component.scss',
    standalone: false
})
export class CustomerDashboardComponent {

  ngOnInit(): void {
    // Fetch the number of bookings made by the user when the component initializes
    this.getBookingsCountByUserIdAndApproved();

    this.getBookingsCountByUserIdAndPending();
  }

  stats = {
    reservations_effectuees: 0,
    reservations_en_cours: 0,
    reservations_annulees: 0,
    voitures_disponibles: 0,
    voitures_en_location: 0,
    voitures_en_entretien: 0,
    voitures_en_reparation: 0

  };

  constructor(
    private customerService: CustomerService,
  ) {}


  getBookingsCountByUserIdAndApproved(): number {
    this.customerService.getBookingsCountByUserIdAndStatus("Approved").subscribe(
      (response: number) => {
        this.stats.reservations_effectuees = response;
        console.log('Approved bookings count:', this.stats.reservations_effectuees);
      },
      (error) => {
        console.error('Error fetching bookings count:', error);
      }
    );
    return 0; // Placeholder return value
  }

  getBookingsCountByUserIdAndPending(): number {
    this.customerService.getBookingsCountByUserIdAndStatus("PENDING").subscribe(
      (response: number) => {
        this.stats.reservations_en_cours = response;
        console.log('Pending bookings count:', this.stats.reservations_en_cours);
      },
      (error) => {
        console.error('Error fetching bookings count:', error);
      }
    );
    return 0; // Placeholder return value
  }
}
