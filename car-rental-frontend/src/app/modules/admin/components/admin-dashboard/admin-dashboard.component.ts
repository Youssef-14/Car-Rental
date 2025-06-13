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
    pending_bookings: 0, // N° pending bookings
    revenue_this_week: 0, // N° revenue this week
    revenue_this_month: 0, // N° revenue this month
    revenue_total: 0 // N° total revenue
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

    // get revenue this week
    this.stats.revenue_this_week = this.getTotalRevenueThisWeek();

    // get revenue this month
    this.stats.revenue_this_month = this.getTotalRevenueThisMonth();

    // get total revenue
    this.stats.revenue_total = this.getTotalRevenue();
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
        this.stats.pending_bookings = response;
        console.log('Bookings count:', this.stats.pending_bookings);
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
        this.stats.bookings_this_week = response;
        console.log('Pending bookings count:', this.stats.bookings_this_week);
      },
      (error) => {
        console.error('Error fetching pending bookings count:', error);
      }
    );
  }

  getTotalRevenueThisWeek(): any {
    this.adminService.getTotalRevenueByThisWeek().subscribe(
      (response: number) => {
        this.stats.revenue_this_week = response;
        console.log('Revenue this week:', this.stats.revenue_this_week);
      },
      (error) => {
        console.error('Error fetching revenue this week:', error);
      }
    );
  }

  getTotalRevenueThisMonth(): any {
    this.adminService.getTotalRevenueByThisMonth().subscribe(
      (response: number) => {
        this.stats.revenue_this_month = response;
        console.log('Revenue this month:', this.stats.revenue_this_month);
      },
      (error) => {
        console.error('Error fetching revenue this month:', error);
      }
    );
  }

  getTotalRevenue(): any {
    this.adminService.getTotalRevenue().subscribe(
      (response: number) => {
        this.stats.revenue_total = response;
        console.log('Total revenue:', this.stats.revenue_total);
      },
      (error) => {
        console.error('Error fetching total revenue:', error);
      }
    );
  }
}
