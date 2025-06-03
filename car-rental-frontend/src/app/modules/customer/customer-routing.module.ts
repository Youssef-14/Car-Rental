import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { CustomerDashboardComponent } from './components/customer-dashboard/customer-dashboard.component'
import { BookCarComponent } from './components/book-car/book-car.component'
import { MyBookingsComponent } from './components/my-bookings/my-bookings.component'
import {MyProfileComponent} from './components/my-profile/my-profile.component';
import {CarListComponent} from './components/car-list/car-list.component';
import {CarFavorisListComponent} from './components/car-favoris-list/car-favoris-list.component';


const routes: Routes = [
  { path: 'dashboard', component: CustomerDashboardComponent },
  { path: 'cars', component: CarListComponent },
  { path: 'cars/favoris', component: CarFavorisListComponent },
  { path: 'book/:id' , component:  BookCarComponent},
  { path: 'my-bookings', component: MyBookingsComponent },
  { path: 'profile', component: MyProfileComponent },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule {}
