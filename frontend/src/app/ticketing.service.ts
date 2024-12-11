import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketingService {
  private tickets = new BehaviorSubject<any[]>([]);
  private logs = new BehaviorSubject<string[]>([]);

  getTickets() {
    return this.tickets.asObservable();
  }

  getLogs() {
    return this.logs.asObservable();
  }

  updateTickets(tickets: any[]) {
    this.tickets.next(tickets);
  }

  addLog(log: string) {
    const updatedLogs = [...this.logs.getValue(), log];
    this.logs.next(updatedLogs);
  }
}
