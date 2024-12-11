import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ControlPanelService {
  private readonly http = inject(HttpClient);

  start(): void {
    const url = 'http://localhost:8080/api/control/start';
    this.http.get<string>(url).subscribe({
      next: (response) => {
        console.log('Start successful:', response);  // Log the response from Spring Boot
      },
      error: (err) => {
        console.error('Start request failed', err);  // Log the error if the request fails
      },
    });
  }

  stop(): void {
    const url = 'http://localhost:8080/api/control/stop';
    this.http.get<string>(url).subscribe({
      next: (response) => {
        console.log('Stop successful:', response);  // Log the response from Spring Boot
      },
      error: (err) => {
        console.error('Stop request failed', err);  // Log the error if the request fails
      },
    });
  }

  getTicketStats(): Observable<{ totalTickets: number; ticketsAdded: number; ticketsBought: number }> {
    const url = 'http://localhost:8080/api/ticket-pool/ticket-stats';
    return this.http.get<{ totalTickets: number; ticketsAdded: number; ticketsBought: number }>(url);
  }
}
