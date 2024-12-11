import {Component, Input} from '@angular/core';
import {RouterLink} from '@angular/router';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-header',
  imports: [RouterLink, CommonModule],  // Imports Angular modules: RouterLink for navigation and CommonModule for basic Angular directives
  templateUrl: './header.component.html',
  standalone: true,  // Marks the component as standalone (doesn't need to be part of a parent module)
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  // Input property to receive whether the configuration form is submitted or not
  @Input() isSubmitted: boolean = false;

  /**
   * Prevents navigation if the form has not been submitted.
   * This method is triggered when navigation is attempted (e.g., clicking the button in the header).
   * If the configuration form is not submitted, the navigation is blocked.
   *
   * @param event - The event triggered by the navigation attempt.
   */
  preventNavigation(event: Event): void {
    if (!this.isSubmitted) {
      event.preventDefault();
      event.stopPropagation();
    }
  }
}
