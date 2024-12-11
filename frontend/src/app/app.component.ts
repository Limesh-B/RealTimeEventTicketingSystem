import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import {SubmissionService} from './services/submission/submission.service';
import {CommonModule} from '@angular/common';

/**
 * Main component of the application that acts as the entry point.
 * It handles the routing and displays the header along with the routing outlet.
 */
@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, CommonModule],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css'
})
export class AppComponent {
  isSubmitted: boolean = false; // Boolean flag to track whether the form has been submitted or not

  /**
   * Constructor that injects the SubmissionService to manage submission state.
   * Subscribes to the submission state to update the 'isSubmitted' flag.
   * @param submissionService - The service that manages the submission state.
   */
  constructor(private submissionService: SubmissionService) {
    // Subscribe to the submission state
    this.submissionService.submitted$.subscribe((submitted) => {
      this.isSubmitted = submitted;  // Update the isSubmitted flag when the state changes
    });
  }
}
