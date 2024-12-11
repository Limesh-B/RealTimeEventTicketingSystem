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

  configuration: Configuration = {
    maxTicketCapacity: this.maxCapacity,
    totalTickets: this.totalTickets,
    ticketReleaseRate: this.releaseRate,
    customerRetrievalRate: this.retrievalRate,
  };

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

  constructor(private configService: ConfigurationFormService, private submissionService: SubmissionService) {

  }


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
    this.configuration = {
      maxTicketCapacity: this.maxCapacity,
      totalTickets: this.totalTickets,
      ticketReleaseRate: this.releaseRate,
      customerRetrievalRate: this.retrievalRate,
    };

    this.configService.addConfig(this.configuration).subscribe(
      (response) => {
        console.log('Configuration added successfully', response);
        this.submissionService.setSubmitted(true);
      },
      (error) => {
        console.error('Error adding configuration', error);
      }
    )
  }
}
