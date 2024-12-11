import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Configuration} from '../../models/configuration';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationFormService {
  private readonly http = inject(HttpClient)

  addConfig(configuration: Configuration): Observable<any> {
    const url = 'http://localhost:8080/api/config/add-config';
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post(url, configuration, { headers });
  }

  getConfig(): Observable<Configuration> {
    const url = 'http://localhost:8080/api/config/get-config';
    return this.http.get<Configuration>(url);
  }
}
