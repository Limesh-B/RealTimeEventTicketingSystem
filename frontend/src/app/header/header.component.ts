import {Component, Input} from '@angular/core';
import {RouterLink} from '@angular/router';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-header',
  imports: [RouterLink, CommonModule],
  templateUrl: './header.component.html',
  standalone: true,
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  @Input() isSubmitted: boolean = false;

  preventNavigation(event: Event): void {
    if (!this.isSubmitted) {
      event.preventDefault();
      event.stopPropagation();
    }
  }
}
