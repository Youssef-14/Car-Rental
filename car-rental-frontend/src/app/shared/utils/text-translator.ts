// if string is pending alors encours if accepted alors accepté
import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})

export class TextTranslator {
  constructor() {
  }

  public translateText(text: string): string {
    if (text === 'PENDING') {
      return 'En cours'
    }
    if (text === 'ACCEPTED') {
      return 'Accepté'
    }
    if (text === 'REJECTED') {
      return 'Rejeté'
    }
    if (text === 'CANCELLED') {
      return 'Annulé'
    }
    return ''
  }
}
