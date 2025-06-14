import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { StorageService } from '../../../auth/components/services/storage/storage.service'
import {environment} from "../../../../environments/environment";

const BASIC_URL = environment.apiUrl
@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(private http: HttpClient) {}

  getAllCars(): Observable<any> {
    return this.http.get(`${BASIC_URL}/customer/cars`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getCarById(id: number): Observable<any> {
    return this.http.get(`${BASIC_URL}/customer/car/${id}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getCarsFavoris(): Observable<any> {
    const userId = StorageService.getUserId()
      ? Number(StorageService.getUserId())
      : 0

    return this.http.get(`${BASIC_URL}/customer/cars/favoris/${userId}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  bookACar(bookACar: any): Observable<any> {
    return this.http.post(`${BASIC_URL}/customer/car/book`, bookACar, {
      headers: this.createAuthorizationHeader()
    })
  }

  getBookingsByUserId(): Observable<any> {
    const userId = StorageService.getUserId()
      ? Number(StorageService.getUserId())
      : 0

    return this.http.get(`${BASIC_URL}/customer/car/bookings/${userId}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  getBookingsCountByUserIdAndStatus(status: string): Observable<any> {
    const userId = StorageService.getUserId()
      ? Number(StorageService.getUserId())
      : 0

    return this.http.get(
      `${BASIC_URL}/customer/car/bookings/count/${userId}/${status}`,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  addCarToFavoris(carId: number): Observable<any> {
    const userId = StorageService.getUserId()
      ? Number(StorageService.getUserId())
      : 0

    return this.http.post(
      `${BASIC_URL}/customer/car/favoris`,
      { userId, carId },
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  getCarFavorisByUserId(): Observable<any> {
    const userId = StorageService.getUserId()
      ? Number(StorageService.getUserId())
      : 0

    return this.http.get(`${BASIC_URL}/customer/car/favoris/${userId}`, {
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

  getProfile(id: number): Observable<any> {
    return this.http.get(`${BASIC_URL}/customer/profile/${id}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  updateProfile(profile: any): Observable<any> {
    return this.http.put(`${BASIC_URL}/customer/update-profile`, profile, {
      headers: this.createAuthorizationHeader()
    })
  }

  getTotalCarsAvailable(): Observable<any> {
    return this.http.get(`${BASIC_URL}/customer/car/available/count`, {
      headers: this.createAuthorizationHeader()
    })
  }

  cancelBooking(id: number): Observable<any> {
    return this.http.put(`${BASIC_URL}/customer/booking/cancel-booking/${id}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  generateVerificationCode(): Observable<any> {
    const userId = StorageService.getUserId()
      ? Number(StorageService.getUserId())
      : 0

    return this.http.post(
      `${BASIC_URL}/customer/generate-verification-token/${userId}`,
      {},
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  verifyUserActivationToken(token: string): Observable<any> {
    const userId = StorageService.getUserId()
      ? Number(StorageService.getUserId())
      : 0
    return this.http.get(
      `${BASIC_URL}/customer/verification-token/${userId}/${token}`,
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  getCarIndisponibleDates(carId: number): Observable<any> {
    return this.http.get(`${BASIC_URL}/customer/car/car_disponibility/${carId}`, {
      headers: this.createAuthorizationHeader()
    })
  }

  addReclamation(reclamation: any): Observable<any> {
    return this.http.post(`${BASIC_URL}/customer/reclamation`, reclamation, {
      headers: this.createAuthorizationHeader()
    })
  }

  getMostBookedCar(): Observable<any> {
    return this.http.get(`${BASIC_URL}/customer/car/most-booked`, {
      headers: this.createAuthorizationHeader()
    })
  }
}
