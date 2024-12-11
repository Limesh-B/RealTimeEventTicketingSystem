import { Routes } from '@angular/router';
import {ControlPanelComponent} from './control-panel/control-panel.component';
import { ConfigurationFormComponent } from './configuration-form/configuration-form.component';

/**
 * Define the application's routes.
 * Each route corresponds to a specific component to load when the route is matched.
 */
export const routes: Routes = [{
  // Default route (empty path), will load the ConfigurationFormComponent
  path: '',
  pathMatch: 'full', loadComponent: () => {
    // Dynamically loads the ConfigurationFormComponent when the path is matched
    return import('./configuration-form/configuration-form.component').then(m => m.ConfigurationFormComponent);
  }
},
  {path: 'control', // Route for '/control', will load the ControlPanelComponent
    loadComponent: () => {
      // Dynamically loads the ControlPanelComponent when the '/control' path is matched
    return import('./control-panel/control-panel.component').then(m => m.ControlPanelComponent);
  }
  }];
