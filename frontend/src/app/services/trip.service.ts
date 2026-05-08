import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TripService {
  private apiUrl = 'http://localhost:8080/api/trips';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  publishTrip(tripData: any): Observable<any> {
    const token = this.authService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post(this.apiUrl, tripData, { headers });
  }

  getTrips(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
