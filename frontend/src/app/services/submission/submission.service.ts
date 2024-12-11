import {Injectable} from '@angular/core';
import { BehaviorSubject } from 'rxjs';

/**
 * Service to manage the submission state of the application.
 * It uses a BehaviorSubject to track whether the configuration form has been submitted or not.
 */
@Injectable({
  providedIn: 'root'
})
export class SubmissionService {
  // BehaviorSubject to hold and manage the submission state
  private submittedSource = new BehaviorSubject<boolean>(false);  // Default is false
  // Observable for subscribers to react to changes in the submission state
  submitted$ = this.submittedSource.asObservable();

  /**
   * Sets the submission state.
   * @param value - The new state of submission (true if submitted, false otherwise).
   */
  setSubmitted(value: boolean): void {
    this.submittedSource.next(value);  // Update the submission state
  }
}
