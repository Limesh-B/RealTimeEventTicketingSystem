import { Component } from '@angular/core';

@Component({
  selector: 'app-control-panel',
  templateUrl: './control-panel.component.html',
  standalone: true,
  styleUrls: ['./control-panel.component.css']
})
export class ControlPanelComponent {
  isRunning: boolean = false; // Tracks if the application is running
  totalTickets: number = 100; // Total number of tickets available
  ticketsSold: number = 0; // Number of tickets sold
  ticketsBought: number = 0; // Number of tickets bought

  // Start the application
  startApplication(): void {
    this.isRunning = true;
  }

  // Stop the application
  stopApplication(): void {
    this.isRunning = false;
  }

  // Sell a ticket (mock implementation)
  sellTicket(): void {
    if (this.isRunning && this.ticketsSold < this.totalTickets) {
      this.ticketsSold++;
    }
  }

  // Buy a ticket (mock implementation)
  buyTicket(): void {
    if (this.isRunning && this.ticketsBought < this.totalTickets) {
      this.ticketsBought++;
    }
  }
}
