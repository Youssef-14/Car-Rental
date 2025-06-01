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
    revenue: 0, // CA
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
}
