package lk.limesh.ticketingapp.threads;

import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.model.Ticket;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments
public class Vendor implements Runnable {
    private int totalTickets; // Tickets the vendor will release
    private int ticketReleaseRate; // Time interval (in seconds) to release a ticket
    private TicketPoolController ticketPool; // Shared resource for managing tickets

    // The run method is executed when the vendor thread starts
    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Vendor " + Thread.currentThread().getName() + " was interrupted");
                break;
            }
            Ticket ticket = new Ticket("Event " + i,new BigDecimal(1000.00));
            ticketPool.addTicket(ticket);
            try {
                Thread.sleep(ticketReleaseRate * 1000L);

            } catch (InterruptedException e) {
                System.out.println("Vendor " + Thread.currentThread().getName() + " was interrupted during sleep");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
