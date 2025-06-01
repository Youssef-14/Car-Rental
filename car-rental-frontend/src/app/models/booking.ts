import {Car} from './car';
import {User} from './user';

export type BookingStatus = 'PENDING' | 'APPROVED' | 'REJECTED'
export interface Booking {
  id: number
  car: Car
  user: User
  price: number
  days: number
  fromDate: string
  toDate: string
  bookCarStatus: BookingStatus
  reservationDate: Date
}
