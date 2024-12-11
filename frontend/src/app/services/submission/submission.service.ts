import {Injectable} from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubmissionService {
  private submittedSource = new BehaviorSubject<boolean>(false);  // Default is false
  submitted$ = this.submittedSource.asObservable();  // Observable for subscribers

  setSubmitted(value: boolean): void {
    this.submittedSource.next(value);  // Set the submitted state
  }
}
