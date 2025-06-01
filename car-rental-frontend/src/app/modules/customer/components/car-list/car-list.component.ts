import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";
import {NzButtonComponent} from "ng-zorro-antd/button";
import {NzColDirective, NzRowDirective} from "ng-zorro-antd/grid";
import {NzWaveDirective} from "ng-zorro-antd/core/wave";
import {RouterLink} from "@angular/router";
import {CustomerService} from '../../services/customer.service';

@Component({
  selector: 'app-car-list',
    imports: [
        NgForOf,
        NzButtonComponent,
        NzColDirective,
        NzRowDirective,
        NzWaveDirective,
        RouterLink
    ],
  templateUrl: './car-list.component.html',
  styleUrl: './car-list.component.scss'
})
export class CarListComponent {
  constructor(private service: CustomerService) {}

  cars: any[] = []

  ngOnInit() {
    this.getAllCars()
  }

  getAllCars() {
    this.service.getAllCars().subscribe(res => {
      res.forEach((car: any) => {
        car.processedImage = `data:image/jpeg;base64,${car.returnedImage}`
        this.cars.push(car)
      })
    })
  }
}
