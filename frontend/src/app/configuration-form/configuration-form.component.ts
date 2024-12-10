import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-configuration-form',
  templateUrl: './configuration-form.component.html',
  standalone: true,
  imports: [
    FormsModule
  ],
  styleUrls: ['./configuration-form.component.css']
})
export class ConfigurationFormComponent {
  maxCapacity: number = 100; // Default value
  totalTickets: number = 100; // Default value
  releaseRate: number = 10; // Default value (tickets per second)
  retrievalRate: number = 5; // Default value (customers per second)

  constructor() {}

  // Handle form submission
  onSubmit(): void {
    console.log('Configuration Submitted:');
    console.log(`Max Capacity: ${this.maxCapacity}`);
    console.log(`Total Tickets: ${this.totalTickets}`);
    console.log(`Ticket Release Rate: ${this.releaseRate}`);
    console.log(`Customer Retrieval Rate: ${this.retrievalRate}`);
    // Add logic to send this configuration to the backend or other components
  }
}
