import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ControlPanelComponent} from './control-panel/control-panel.component';
import {HeaderComponent} from './header/header.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ControlPanelComponent, HeaderComponent],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
