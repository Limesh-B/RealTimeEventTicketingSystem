import { Component, inject, OnInit, OnDestroy } from '@angular/core';
import { ControlPanelService } from '../services/control-panel/control-panel.service';
import {interval, Subscription, switchMap} from 'rxjs';
import {NgStyle} from '@angular/common';

@Component({
  selector: 'app-control-panel',
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.css'],
  imports: [
    NgStyle
  ],
  standalone: true
})
export class ControlPanelComponent implements OnInit, OnDestroy {
  isRunning: boolean = false; // Tracks if the application is running
  totalTickets = 0;
  ticketsAdded = 0;
  ticketsBought = 0;
  private pollingSubscription?: Subscription;

  controlPanelService = inject(ControlPanelService);

  ngOnInit(): void {
    console.log('Starting ticket stats polling...');
    this.pollingSubscription = interval(1000)
      .pipe(switchMap(() => this.controlPanelService.getTicketStats()))
      .subscribe({
        next: (stats) => {
          console.log('Received ticket stats:', stats);
          this.totalTickets = stats.totalTickets;
          this.ticketsAdded = stats.ticketsAdded;
          this.ticketsBought = stats.ticketsBought;
        },
        error: (err) => {
          console.error('Failed to fetch ticket stats:', err);
        },
      });
  }

  startApplication(): void {
    this.isRunning = true;
    this.controlPanelService.start();
  }

  stopApplication(): void {
    this.isRunning = false;
    this.controlPanelService.stop();
  }

  ngOnDestroy(): void {
    // Unsubscribe from polling when the component is destroyed
    this.pollingSubscription?.unsubscribe();
  }
}
