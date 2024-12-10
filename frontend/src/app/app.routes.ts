import { Routes } from '@angular/router';
import {ControlPanelComponent} from './control-panel/control-panel.component';
import { ConfigurationFormComponent } from './configuration-form/configuration-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'control-panel', pathMatch: 'full' },
  { path: 'control-panel', component: ControlPanelComponent },
  { path: 'configuration-form', component: ConfigurationFormComponent }
];
