import {Component, inject} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {ConfigurationFormService} from '../services/configuration-form/configuration-form.service';
import {SubmissionService} from '../services/submission/submission.service';
import {Configuration} from '../models/configuration';


@Component({
  selector: 'app-configuration-form',
  templateUrl: './configuration-form.component.html',
  styleUrls: ['./configuration-form.component.css'],
  standalone: true,  // Marks the component as standalone (it doesn't depend on a parent module for dependencies)
  imports: [
    CommonModule,  // Angular CommonModule for common directives like ngIf, ngFor
    FormsModule,  // FormsModule to support template-driven forms
    ReactiveFormsModule,  // ReactiveFormsModule to support reactive forms
  ]
})
export class ConfigurationFormComponent {
  // Default values for configuration
  maxCapacity: number = 100;
  totalTickets: number = 50;
  releaseRate: number = 10;
  retrievalRate: number = 5;

  // Initial configuration object
  configuration: Configuration = {
    maxTicketCapacity: this.maxCapacity,
    totalTickets: this.totalTickets,
    ticketReleaseRate: this.releaseRate,
    customerRetrievalRate: this.retrievalRate,
  };

  /**
   * ngOnInit lifecycle hook that runs when the component is initialized.
   * Retrieves configuration data using ConfigurationFormService and updates component state.
   */
  ngOnInit(): void {
    this.configService.getConfig().subscribe({
      next: (response) => {
        console.log('Configuration retrieved:', response);
        this.maxCapacity = response.maxTicketCapacity;
        this.totalTickets = response.totalTickets;
        this.releaseRate = response.ticketReleaseRate;
        this.retrievalRate = response.customerRetrievalRate;
      },
      error: (error) => {
        console.error('Error retrieving configuration:', error);
      }
    });
  }

  /**
   * Constructor with dependency injection of services.
   * @param configService Service for managing configuration data.
   * @param submissionService Service for tracking submission status.
   */
  constructor(private configService: ConfigurationFormService, private submissionService: SubmissionService) {
  }

  /**
   * Validates the input fields to ensure positive values for ticket-related properties.
   * Sets custom validation messages if input is invalid.
   * @param event Event triggered by user input
   * @param field Field name for validation context
   */
  validatePositiveNumber(event: any, field: String): void {
    const value = event.target.value;
    if (value <= 0) {
      event.target.setCustomValidity('Value must be greater than 0');
    } else {
      event.target.setCustomValidity('');
    }
  }


  /**
   * Handles the form submission process.
   * Collects data from the form and sends it to the backend server using the ConfigurationFormService.
   */
  onSubmit(): void {
    this.configuration = {
      maxTicketCapacity: this.maxCapacity,
      totalTickets: this.totalTickets,
      ticketReleaseRate: this.releaseRate,
      customerRetrievalRate: this.retrievalRate,
    };

    // Calls the service to add the configuration
    this.configService.addConfig(this.configuration).subscribe(
      (response) => { // Handles successful submission
        console.log('Configuration added successfully', response);
        this.submissionService.setSubmitted(true);  // Marks the form as submitted
      },
      (error) => { // Handles error during submission
        console.error('Error adding configuration', error);
      }
    )
  }
}
