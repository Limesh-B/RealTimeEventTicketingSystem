package lk.limesh.ticketingapp.threads;

import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.model.Ticket;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class Vendor implements Runnable {
    private int totalTickets; // Tickets the vendor will release
    private int ticketReleaseRate; // Time interval (in seconds) to release a ticket
    private TicketPoolController ticketPool; // Shared resource for managing tickets

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            Ticket ticket = new Ticket("Event " + i,new BigDecimal(1000.00));
            ticketPool.addTicket(ticket);
            try {
                Thread.sleep(ticketReleaseRate * 250L);

            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
