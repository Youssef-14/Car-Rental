import { Component } from '@angular/core'
import { FormBuilder, FormGroup } from '@angular/forms'
import { AdminService } from '../../services/admin.service'
import {TextTranslator} from '../../../../shared/utils/text-translator';

@Component({
    selector: 'app-search-car',
    templateUrl: './search-car.component.html',
    styleUrl: './search-car.component.scss',
    standalone: false
})
export class SearchCarComponent {
  searchCarForm!: FormGroup
  listOfOption: Array<{ label: string; value: string }> = []
  listOfBrands = ['Toyota', 'Honda', 'BMW', 'Mercedes', 'Audi', 'Lexus', 'Lamborghini', 'Porche', 'Ford', 'Fiat', 'Other']
  listOfType = ['Hyper Car','Sports Car','Luxury Car', 'Suv', 'Sedan','Diesel', 'Crossover']
  listOfColor = ['Red', 'Blue', 'Brown', 'Green', 'Black', 'White', 'Silver', 'Other']
  listOfTransmission = ['Manual', 'Automatic']
  isSpinning = false
  cars: any[] = []

  constructor(
    private fb: FormBuilder,
    private service: AdminService
  ) {
    this.searchCarForm = this.fb.group({
      brand: [null],
      type: [null],
      transmission: [null],
      color: [null]
    })
  }

  searchCar() {
    this.isSpinning = true
    this.service.searchCar(this.searchCarForm.value).subscribe(
      res => {
        this.isSpinning = false

        this.cars = []

        const carDtoList = res.carDtoList

        carDtoList.forEach((car: any) => {
          car.processedImage = `data:image/jpeg;base64,${car.returnedImage}`
          this.cars.push(car)
        })
      },
      err => {
        this.isSpinning = false
      }
    )
  }

  protected readonly TextTranslator = TextTranslator;
}
