import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import {SubmissionService} from './services/submission/submission.service';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, CommonModule],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css'
})
export class AppComponent {
  isSubmitted: boolean = false;

  constructor(private submissionService: SubmissionService) {
    // Subscribe to the submission state
    this.submissionService.submitted$.subscribe((submitted) => {
      this.isSubmitted = submitted;
    });
  }
}
