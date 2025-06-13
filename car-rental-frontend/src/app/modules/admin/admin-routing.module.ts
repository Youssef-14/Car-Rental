import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component'
import { PostCarComponent } from './components/post-car/post-car.component'
import { UpdateCarComponent } from './components/update-car/update-car.component'
import { GetBookingsComponent } from './components/get-bookings/get-bookings.component'
import { SearchCarComponent } from './components/search-car/search-car.component'
import {GetCarsComponent} from "./components/get-cars/get-cars.component";
import {GetUsersComponent} from './components/get-users/get-users.component';
import {GetCarBookingsComponent} from './components/get-car-bookings/get-car-bookings.component';
import {GetReclamationsComponent} from './components/get-reclamations/get-reclamations.component';

const routes: Routes = [
  { path: 'dashboard', component: AdminDashboardComponent },
  { path: 'car', component: GetCarsComponent },
  { path: 'car/add', component: PostCarComponent },
  { path: 'car/search', component: SearchCarComponent },
  { path: 'car/update/:id', component: UpdateCarComponent },
  { path: 'car/bookings/:id', component: GetCarBookingsComponent},
  { path: 'bookings', component: GetBookingsComponent },
  { path: 'reclamations', component: GetReclamationsComponent },
  { path: 'users', component: GetUsersComponent },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: '**', redirectTo: 'dashboard' } // Wildcard route for a 404 page
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
