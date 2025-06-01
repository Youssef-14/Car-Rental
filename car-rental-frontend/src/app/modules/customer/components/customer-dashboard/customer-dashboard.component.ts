import { Component } from '@angular/core'

@Component({
    selector: 'app-customer-dashboard',
    templateUrl: './customer-dashboard.component.html',
    styleUrl: './customer-dashboard.component.scss',
    standalone: false
})
export class CustomerDashboardComponent {
  stats = {
    revenue: 245000, // CA
    users: 1225, // N° users
    activeSessions: 328,
    newSubscriptions: 56
  };

  constructor() {}

  ngOnInit(): void {}
}
