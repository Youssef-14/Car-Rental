import { Component, OnInit } from '@angular/core';
import {AdminService} from '../../services/admin.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss'],
  standalone: false
})
export class AdminDashboardComponent implements OnInit {
  stats = {
    users: 0,// N° users
    cars: 0, // N° cars
    bookings_this_week: 0, // N° bookings this week
    pending_bookings: 0 // N° pending bookings
  };

  constructor(
    private adminService: AdminService,
  ) {}

  ngOnInit(): void {
    // Fetch the number of customers when the component initializes
    this.stats.users = this.getCustomersCount();

    // Fetch the number of bookings when the component initializes
    this.stats.pending_bookings = this.getBookingsCount();

    // get number of cars
    this.stats.cars = this.getCarsCount();

    // get number of bookings this month
    this.stats.bookings_this_week = this.getTotalBookingsCountThisMonth();
  }

  // get customers count
  getCustomersCount(): any {
    this.adminService.getTotalCustomersCount().subscribe(
      (response: number) => {
        this.stats.users = response;
        console.log('Customer count:', this.stats.users);
      },
      (error) => {
        console.error('Error fetching customer count:', error);
      }
    );
  }

  // get bookings count
  getBookingsCount(): any {
    this.adminService.getTotalBookingsCountByStatus("PENDING").subscribe(
      (response: number) => {
        this.stats.bookings_this_week = response;
        console.log('Bookings count:', this.stats.bookings_this_week);
      },
      (error) => {
        console.error('Error fetching bookings count:', error);
      }
    );
  }

  // get cars count
  getCarsCount(): any {
    this.adminService.getTotalCarsCount().subscribe(
      (response: number) => {
        this.stats.cars = response;
        console.log('Car count:', this.stats.cars);
      },
      (error) => {
        console.error('Error fetching car count:', error);
      }
    );
  }

  getTotalBookingsCountThisMonth(): any {
    this.adminService.getTotalBookingsCountThisMonth().subscribe(
      (response: number) => {
        this.stats.pending_bookings = response;
        console.log('Pending bookings count:', this.stats.pending_bookings);
      },
      (error) => {
        console.error('Error fetching pending bookings count:', error);
      }
    );
  }
}
