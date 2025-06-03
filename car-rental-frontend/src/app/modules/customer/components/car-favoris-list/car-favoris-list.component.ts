import { Component } from '@angular/core';
import {CustomerService} from '../../services/customer.service';
import {NgForOf} from '@angular/common';
import {NzButtonComponent} from 'ng-zorro-antd/button';
import {NzColDirective, NzRowDirective} from 'ng-zorro-antd/grid';
import {NzWaveDirective} from 'ng-zorro-antd/core/wave';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-car-favoris-list',
  imports: [
    NgForOf,
    NzButtonComponent,
    NzColDirective,
    NzRowDirective,
    NzWaveDirective,
    RouterLink
  ],
  templateUrl: './car-favoris-list.component.html',
  styleUrl: './car-favoris-list.component.scss'
})
export class CarFavorisListComponent {
  constructor(private service: CustomerService) {}

  cars: any[] = []

  ngOnInit() {
    this.getCarsFavoris()
  }

  getCarsFavoris() {
    this.service.getCarsFavoris().subscribe(res => {
      res.forEach((car: any) => {
        car.processedImage = `data:image/jpeg;base64,${car.returnedImage}`
        this.cars.push(car)
      })
    })
  }

}
