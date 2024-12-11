import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // For Template-driven Forms
import { ReactiveFormsModule } from '@angular/forms'; // For Reactive Forms (if needed)
import { CommonModule } from '@angular/common'; // CommonModule for Angular components

import {NgIf} from '@angular/common';

@Component({
  selector: 'app-configuration-form',
  templateUrl: './configuration-form.component.html',
  styleUrls: ['./configuration-form.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class ConfigurationFormComponent {
  maxCapacity: number = 100;
  totalTickets: number = 50;
  releaseRate: number = 10;
  retrievalRate: number = 5;

  constructor() {}

  validatePositiveNumber(event: any, field: String): void {
    const value = event.target.value;
    if (value <= 0) {
      event.target.setCustomValidity('Value must be greater than 0');
    } else {
      event.target.setCustomValidity('');
    }
  }


  // Handle form submission
  onSubmit(): void {
    console.log('Configuration Submitted:');
    console.log('Max Capacity: ${this.maxCapacity}');
    console.log('Total Tickets: ${this.totalTickets}');
    console.log('Ticket Release Rate: ${this.releaseRate}');
    console.log('Customer Retrieval Rate: ${this.retrievalRate}');
  }
}
