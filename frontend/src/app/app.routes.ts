import { Routes } from '@angular/router';
import {ControlPanelComponent} from './control-panel/control-panel.component';
import { ConfigurationFormComponent } from './configuration-form/configuration-form.component';

export const routes: Routes = [{
  path: '', pathMatch: 'full', loadComponent: () => {
    return import('./configuration-form/configuration-form.component').then(m => m.ConfigurationFormComponent);
  }
},
  {path: 'control', loadComponent: () => {
    return import('./control-panel/control-panel.component').then(m => m.ControlPanelComponent);
  }
  }];
