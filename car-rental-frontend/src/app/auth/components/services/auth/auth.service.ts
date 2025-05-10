import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { environment} from "../../../../../environments/environment";

const BASE_URL = environment.apiUrl

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) {}

  customerRegister(signupRequest: any): Observable<any> {
    return this.http.post(`${BASE_URL}/auth/customer-register`, signupRequest)
  }

  agentRegister(signupRequest: any): Observable<any> {
    return this.http.post(`${BASE_URL}/auth/agent-register`, signupRequest)
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(`${BASE_URL}/auth/authenticate`, loginRequest)
  }
}
