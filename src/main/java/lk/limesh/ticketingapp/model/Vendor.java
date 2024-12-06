package lk.limesh.ticketingapp.model;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class Vendor implements Runnable {
    private int totalTickets; // Tickets willing to sell
    private int ticketReleaseRate; // Frequency of releasing
    private TicketPool ticketPool; // Shared resource between Vendors and Customers


    @Override
    public void run() {
        for (int i = 1; i < totalTickets; i++) {
            Ticket ticket = new Ticket("Simple Event", new BigDecimal("1000"));
            ticketPool.addTicket(ticket);
            try {
                Thread.sleep(ticketReleaseRate * 1000); // To calculate to MS
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
