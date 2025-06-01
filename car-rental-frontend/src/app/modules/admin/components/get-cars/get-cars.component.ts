import {Component, OnInit} from '@angular/core';
import {AdminService} from "../../services/admin.service";
import {NzMessageService} from "ng-zorro-antd/message";
import {Car} from '../../../../models/car';

@Component({
  selector: 'app-get-cars',
  standalone: false,

  templateUrl: './get-cars.component.html',
  styleUrl: './get-cars.component.scss'
})
export class GetCarsComponent implements OnInit {
  cars: Car[] = []

  constructor(
      private adminService: AdminService,
      private message: NzMessageService
  ) {}

  ngOnInit() {
    this.getAllCars()
  }

  getAllCars() {
    this.adminService.getAllCars().subscribe(res => {
      res.forEach((car: any) => {
        car.processedImage = `data:image/jpeg;base64,${car.returnedImage}`
        this.cars.push(car)
      })
    })
  }

  deleteCar(id: number) {
    this.adminService.deleteCar(id).subscribe(res => {
      this.cars = this.cars.filter(car => car.id !== id)

      this.message.success('Car deleted successfully', { nzDuration: 3000 })
    })
  }

  hideShowCar(id: number) {
    this.adminService.hideShowCar(id).subscribe(res => {
      const car = this.cars.find(car => car.id === id);
      if (car) {
        car.available = !car.available;
        this.message.success(`Voiture ${car.available ? 'cachée' : 'visible'} avec succès`, { nzDuration: 3000 });
      }
    })
  }
}
