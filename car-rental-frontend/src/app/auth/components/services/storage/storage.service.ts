import { Injectable } from '@angular/core'

const PREFIX_TOKEN_KEY = 'car_rental'

const TOKEN = `${PREFIX_TOKEN_KEY}.token`
const USER = `${PREFIX_TOKEN_KEY}.user`
const PROFILE = `${PREFIX_TOKEN_KEY}.profile`

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() {}

  static saveToken(token: string) {
    localStorage.removeItem(TOKEN)
    localStorage.setItem(TOKEN, token)
  }

  static saveUser(user: any) {
    localStorage.removeItem(USER)
    localStorage.setItem(USER, JSON.stringify(user))
  }

  static saveProfile(profile: any) {
    localStorage.removeItem(PROFILE)
    localStorage.setItem(PROFILE, JSON.stringify(profile))
  }

  static verifyUserActivationToken() {
    // update profile with isVerified true
    const profile = this.getProfile()
    if (profile) {
      profile.isVerified = true
      this.saveProfile(profile)
    }
  }

  static getToken(): string | null {
    return localStorage.getItem(TOKEN)
  }

  static getUser(): any {
    return JSON.parse(localStorage.getItem(USER) || '{}')
  }

  static getProfile(): any {
    return JSON.parse(localStorage.getItem(PROFILE) || '{}')
  }

  static getUserId(): string {
    const user = this.getUser()

    if (user == null) return ''

    return user.id
  }

  static getUserRole(): string {
    const user = this.getUser()

    if (user == null) return ''

    return user.role
  }

  static isLoggedIn(): boolean {
    return this.getToken() != null
  }

  static isAdminLoggedIn(): boolean {
    if (this.getToken() == null) return false

    return this.getUserRole() === 'ADMIN'
  }

  static isCustomerLoggedIn(): boolean {
    if (this.getToken() == null) return false

    return this.getUserRole() === 'CUSTOMER'
  }

  static isAgentLoggedIn(): boolean {
    if (this.getToken() == null) return false

    return this.getUserRole() === 'AGENT'
  }

  static logout() {
    localStorage.removeItem(TOKEN)
    localStorage.removeItem(USER)
    localStorage.removeItem(PROFILE)
    localStorage.removeItem(`${PREFIX_TOKEN_KEY}.favorites`)
  }

  static addFavorites(carIds: number[]) {
    const favorites = this.getFavorites()
    carIds.forEach(carId => {
      if (!favorites.includes(carId)) {
        favorites.push(carId)
      }
    })
    localStorage.setItem(
      `${PREFIX_TOKEN_KEY}.favorites`,
      JSON.stringify(favorites)
    )
  }

  // liste des favoris
  static addFavorite(carId: number) {
    const favorites = this.getFavorites()
    if (!favorites.includes(carId)) {
      favorites.push(carId)
      localStorage.setItem(
        `${PREFIX_TOKEN_KEY}.favorites`,
        JSON.stringify(favorites)
      )
      return true;
    }
    return false;
  }

  static removeFavorite(carId: number) {
    const favorites = this.getFavorites()
    const index = favorites.indexOf(carId)
    if (index > -1) {
      favorites.splice(index, 1)
      localStorage.setItem(
        `${PREFIX_TOKEN_KEY}.favorites`,
        JSON.stringify(favorites)
      )
      return true;
    }
    return false;
  }

  static getFavorites(): number[] {
    const favorites = localStorage.getItem(`${PREFIX_TOKEN_KEY}.favorites`)
    return favorites ? JSON.parse(favorites) : []
  }
}
