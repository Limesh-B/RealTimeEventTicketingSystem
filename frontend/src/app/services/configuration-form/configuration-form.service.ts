import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Configuration} from '../../models/configuration';
import {Observable} from 'rxjs';

/**
 * Service for handling HTTP requests related to configuration management.
 * This service allows adding and retrieving configuration settings from the backend.
 */
@Injectable({
  providedIn: 'root'
})
export class ConfigurationFormService {
  private readonly http = inject(HttpClient) // Injects the HttpClient service for making HTTP requests

  /**
   * Sends a POST request to the backend to add a new configuration.
   * @param configuration The configuration object to be added.
   * @returns Observable that emits the response from the backend.
   */
  addConfig(configuration: Configuration): Observable<any> {
    const url = 'http://localhost:8080/api/config/add-config';  // Backend endpoint for adding configuration
    const headers = { 'Content-Type': 'application/json' };  // Setting the content type to JSON
    return this.http.post(url, configuration, { headers });  // Makes the POST request with the configuration data
  }

  /**
   * Sends a GET request to the backend to retrieve the current configuration.
   * @returns Observable that emits the configuration object retrieved from the backend.
   */
  getConfig(): Observable<Configuration> {
    const url = 'http://localhost:8080/api/config/get-config';  // Backend endpoint for retrieving configuration
    return this.http.get<Configuration>(url);  // Makes the GET request and returns the configuration object
  }
}
