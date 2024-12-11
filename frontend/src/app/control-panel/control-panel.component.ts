import { Component, inject, OnInit, OnDestroy } from '@angular/core';
import { ControlPanelService } from '../services/control-panel/control-panel.service';
import {interval, Subscription, switchMap} from 'rxjs';
import {NgStyle} from '@angular/common';

@Component({
  selector: 'app-control-panel',
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.css'],
  imports: [
    NgStyle  // Angular directive used for dynamically setting styles
  ],
  standalone: true // Marks the component as standalone (it doesn't depend on a parent module for dependencies)
})
export class ControlPanelComponent implements OnInit, OnDestroy {
  isRunning: boolean = false; // Flags whether the application is running or not

  // Variables to store ticket statistics
  totalTickets = 0;
  ticketsAdded = 0;
  ticketsBought = 0;

  // Subscription to manage polling for ticket stats
  private pollingSubscription?: Subscription;

  // Dependency injection of ControlPanelService to interact with backend
  controlPanelService = inject(ControlPanelService);

  /**
   * ngOnInit lifecycle hook to initialize the component.
   * Starts polling for ticket stats using RxJS interval and switchMap.
   * The polling interval is set to 0.5 seconds (500ms).
   */
  ngOnInit(): void {
    console.log('Starting ticket stats polling...');
    this.pollingSubscription = interval(500)  // Poll every 0.5 seconds
      .pipe(switchMap(() => this.controlPanelService.getTicketStats()))  // Switch to the observable that fetches ticket stats
      .subscribe({
        next: (stats) => {  // Handle the response with the updated ticket stats
          console.log('Received ticket stats:', stats);
          this.totalTickets = stats.totalTickets;
          this.ticketsAdded = stats.ticketsAdded;
          this.ticketsBought = stats.ticketsBought;
        },
        error: (err) => {  // Handle error response
          console.error('Failed to fetch ticket stats:', err);
        },
      });
  }

  /**
   * Starts the application by setting the `isRunning` flag to true
   * and calling the `start()` method on the ControlPanelService.
   */
  startApplication(): void {
    this.isRunning = true;
    this.controlPanelService.start();  // Start the service
  }

  /**
   * Stops the application by setting the `isRunning` flag to false
   * and calling the `stop()` method on the ControlPanelService.
   */
  stopApplication(): void {
    this.isRunning = false;
    this.controlPanelService.stop();  // Stop the service
  }

  /**
   * ngOnDestroy lifecycle hook to clean up resources when the component is destroyed.
   * Unsubscribes from the polling subscription to prevent memory leaks.
   */
  ngOnDestroy(): void {
    this.pollingSubscription?.unsubscribe(); // Unsubscribe from polling when the component is destroyed
  }
}
