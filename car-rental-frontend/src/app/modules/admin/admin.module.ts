import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'

import { AdminRoutingModule } from './admin-routing.module'
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component'
import { PostCarComponent } from './components/post-car/post-car.component'
import { NgZorroImportsModule } from '../../NgZorroImportsModule'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UpdateCarComponent } from './components/update-car/update-car.component';
import { GetBookingsComponent } from './components/get-bookings/get-bookings.component';
import { SearchCarComponent } from './components/search-car/search-car.component';
import { GetCarsComponent } from './components/get-cars/get-cars.component';
import {NzTableComponent, NzThAddOnComponent} from 'ng-zorro-antd/table';
import {NzDropDownDirective, NzDropdownMenuComponent} from 'ng-zorro-antd/dropdown';
import {NzAvatarComponent} from 'ng-zorro-antd/avatar';
import {NzCardComponent} from 'ng-zorro-antd/card';
import {NzListComponent, NzListItemComponent} from 'ng-zorro-antd/list';
import {G2MiniBarComponent} from '@delon/chart/mini-bar';
import {QuickMenuComponent} from '@delon/abc/quick-menu';
import {G2BarComponent} from '@delon/chart/bar';
import {G2TimelineComponent} from '@delon/chart/timeline';

@NgModule({
  declarations: [AdminDashboardComponent, PostCarComponent, UpdateCarComponent, GetBookingsComponent, SearchCarComponent, GetCarsComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    NgZorroImportsModule,
    ReactiveFormsModule,
    FormsModule,
    NzTableComponent,
    NzThAddOnComponent,
    NzDropdownMenuComponent,
    NzDropDownDirective,
    NzAvatarComponent,
    NzCardComponent,
    NzListItemComponent,
    NzListComponent,
    G2MiniBarComponent,
    QuickMenuComponent,
    G2BarComponent,
    G2TimelineComponent
  ]
})
export class AdminModule {}
