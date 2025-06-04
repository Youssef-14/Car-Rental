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

    // afficher une boîte de dialogue de confirmation avant de supprimer la voiture
    // message confirmation before changing status
    if (!window.confirm(`Êtes-vous sûr de vouloir supprimer cette voiture,
      cette action est irréversible ?`)) {
      return
    }

    this.adminService.deleteCar(id).subscribe(res => {
      this.cars = this.cars.filter(car => car.id !== id)

      this.message.success('Car deleted successfully', { nzDuration: 3000 })
    })
  }

  hideShowCar(id: number) {
    this.adminService.hideShowCar(id).subscribe(res => {

      // message de confirmation avant de changer le statut de la voiture
      if (!window.confirm(`Êtes-vous sûr de vouloir ${this.cars.find(car => car.id === id)?.available ? 'cacher' : 'afficher'} cette voiture ?
      aucune réservation ne sera plus possible pour cette voiture si elle est cachée.`)) {
        return
      }

      const car = this.cars.find(car => car.id === id);
      if (car) {
        car.available = !car.available;
        this.message.success(`Voiture ${car.available ? 'visible' : 'cachée'} avec succès`, { nzDuration: 3000 });
      }
    })
  }
}
