import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { StorageService } from '../../../auth/components/services/storage/storage.service'
import {environment} from "../../../../environments/environment";

const BASIC_URL = environment.apiUrl

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http: HttpClient) {}

  postCar(carDto: any): Observable<any> {
    return this.http.post(`${BASIC_URL}/admin/car`, carDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  getAllCars(): Observable<any> {
    return this.http.get(`${BASIC_URL}/admin/cars`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getTotalCarsCount(): Observable<number> {
    return this.http.get<number>(`${BASIC_URL}/admin/cars/count`, {
      headers: this.createAuthorizationHeader()
    });
  }

  getAllUser(): Observable<any> {
    return this.http.get(`${BASIC_URL}/admin/users`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getTotalCustomersCount(): Observable<number> {
    return this.http.get<number>(`${BASIC_URL}/admin/customers/count`, {
      headers: this.createAuthorizationHeader()
    });
  }

  getTotalBookingsCountByStatus(status: string): Observable<number> {
    return this.http.get<number>(`${BASIC_URL}/admin/bookings/count/status/${status}`, {
      headers: this.createAuthorizationHeader()
    });
  }

  getTotalBookingsCountThisMonth(): Observable<number> {
    return this.http.get<number>(`${BASIC_URL}/admin/bookings/count/this_month`, {
      headers: this.createAuthorizationHeader()
    });
  }

  deleteCar(id: number): Observable<any> {
    return this.http.delete(`${BASIC_URL}/admin/car/${id}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getCarById(id: number): Observable<any> {
    return this.http.get(`${BASIC_URL}/admin/car/${id}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  updateCar(carId: number, carDto: any): Observable<any> {
    return this.http.put(`${BASIC_URL}/admin/car/${carId}`, carDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  getCarBookings(): Observable<any> {
    return this.http.get(`${BASIC_URL}/admin/car/bookings`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getCarBookingsByStatus(status:String):Observable<any> {
    return this.http.get(`${BASIC_URL}/admin/car/bookings/status/${status}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getCarBookingsByCar(id:number):Observable<any> {
    return this.http.get(`${BASIC_URL}/admin/car/bookings/car/${id}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  changeBookingStatus(bookingId: number, status: string): Observable<any> {
    return this.http.get(
      `${BASIC_URL}/admin/car/booking/${bookingId}/${status}`,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  searchCar(searchDto: any): Observable<any> {
    return this.http.post(`${BASIC_URL}/admin/car/search`, searchDto, {
      headers: this.createAuthorizationHeader()
    })
  }

  private createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders()

    return authHeaders.set(
      'Authorization',
      `Bearer ${StorageService.getToken()}`
    )
  }

  hideShowCar(carId: number): Observable<any> {
    return this.http.put(`${BASIC_URL}/admin/car/hide/${carId}`, {}, {
      headers: this.createAuthorizationHeader()
    })
  }

  getTotalRevenue(): Observable<number> {
    return this.http.get<number>(`${BASIC_URL}/admin/revenue/total`, {
      headers: this.createAuthorizationHeader()
    });
  }

  getTotalRevenueByThisMonth(): Observable<number> {
    return this.http.get<number>(`${BASIC_URL}/admin/revenue/this_month`, {
      headers: this.createAuthorizationHeader()
    });
  }

  getTotalRevenueByThisWeek(): Observable<number> {
    return this.http.get<number>(`${BASIC_URL}/admin/revenue/this_week`, {
      headers: this.createAuthorizationHeader()
    });
  }

  getReclamations(): Observable<any> {
    return this.http.get(`${BASIC_URL}/admin/reclamations`, {
      headers: this.createAuthorizationHeader()
    })
  }
}
