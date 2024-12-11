import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import {Observable} from 'rxjs';

/**
 * Service for controlling the ticketing application's control panel and retrieving ticket statistics.
 * It communicates with the backend API to start and stop the application, as well as fetch ticket statistics.
 */
@Injectable({
  providedIn: 'root',
})
export class ControlPanelService {
  private readonly http = inject(HttpClient);  // Injecting HttpClient for making HTTP requests

  /**
   * Starts the application by sending a request to the backend.
   * Logs the response or error on success or failure respectively on the console.
   */
  start(): void {
    const url = 'http://localhost:8080/api/control/start';  // Backend endpoint for starting the application
    this.http.get<string>(url).subscribe({
      next: (response) => {
        console.log('Start successful:', response);  // Log the response from Spring Boot if the request is successful
      },
      error: (err) => {
        console.error('Start request failed', err);  // Log the error if the request fails
      },
    });
  }

  /**
   * Stops the application by sending a request to the backend.
   * Logs the response or error on success or failure respectively.
   */
  stop(): void {
    const url = 'http://localhost:8080/api/control/stop';  // Backend endpoint for stopping the application
    this.http.get<string>(url).subscribe({
      next: (response) => {
        console.log('Stop successful:', response);  // Log the response from Spring Boot if the request is successful
      },
      error: (err) => {
        console.error('Stop request failed', err);  // Log the error if the request fails
      },
    });
  }

  /**
   * Retrieves the ticket statistics from the backend.
   * @returns An observable with the ticket statistics, including totalTickets, ticketsAdded, and ticketsBought.
   */
  getTicketStats(): Observable<{ totalTickets: number; ticketsAdded: number; ticketsBought: number }> {
    const url = 'http://localhost:8080/api/ticket-pool/ticket-stats';  // Backend endpoint for retrieving ticket stats
    return this.http.get<{ totalTickets: number; ticketsAdded: number; ticketsBought: number }>(url);  // Fetch and return the ticket stats
  }
}
