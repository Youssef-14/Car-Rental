import {Component, OnInit} from '@angular/core';
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {NzButtonComponent} from "ng-zorro-antd/button";
import {NzColDirective, NzRowDirective} from "ng-zorro-antd/grid";
import {NzWaveDirective} from "ng-zorro-antd/core/wave";
import {RouterLink} from "@angular/router";
import {CustomerService} from '../../services/customer.service';
import {StorageService} from '../../../../auth/components/services/storage/storage.service';

@Component({
  selector: 'app-car-list',
  imports: [
    NgForOf,
    NzButtonComponent,
    NzColDirective,
    NzRowDirective,
    NzWaveDirective,
    RouterLink,
    NgClass,
    NgIf,
  ],
  templateUrl: './car-list.component.html',
  styleUrl: './car-list.component.scss'
})
export class CarListComponent implements OnInit {
  constructor(private service: CustomerService) {}

  cars: any[] = []

  ngOnInit() {
    this.getAllCars()
  }

  getAllCars() {
    this.service.getAllCars().subscribe(res => {
      res.forEach((car: any) => {
        car.processedImage = `data:image/jpeg;base64,${car.returnedImage}`
        this.service.getCarFavorisByUserId().subscribe(favoris => {
          StorageService.addFavorites(res.map((car: any) => car.id))
          car.isFavoris = favoris.some((fav: any) => fav.id === car.id)

          if (car.isFavoris) {
            car.favorisIcon = 'ant-design:heart-filled'
          } else {
            car.favorisIcon = 'ant-design:heart-outlined'
          }
        }
        )
        this.cars.push(car)
      })
    })
  }

  toggleFavoris(car: any) {
    if (car.isFavoris) {
      this.service.addCarToFavoris(car.id).subscribe(() => {
        car.isFavoris = false
      })
      StorageService.addFavorite(car.id);
    } else {
      this.service.addCarToFavoris(car.id).subscribe(() => {
        car.isFavoris = true
      })
      StorageService.removeFavorite(car.id);
    }
  }
}
