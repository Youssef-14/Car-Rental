// if string is pending alors encours if accepted alors accepté
import { Injectable } from '@angular/core';
import {BookingStatus} from '../../models/booking';
@Injectable({
  providedIn: 'root'
})
@Injectable({
  providedIn: 'root'
})
export class TextTranslator {
  constructor() {}

  public static translateText(status: BookingStatus): string {
    switch (status) {
      case 'PENDING':
        return 'En cours';
      case 'APPROVED':
        return 'Accepté';
      case 'REJECTED':
        return 'Rejeté';
      default:
        return 'Statut inconnu';
    }
  }
}
