package lk.limesh.ticketingapp.threads;

import lk.limesh.ticketingapp.model.Ticket;
import lk.limesh.ticketingapp.model.TicketPool;
import lk.limesh.ticketingapp.service.TicketPoolService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class Vendor implements Runnable {
    private int totalTickets; // Tickets the vendor will release
    private int ticketReleaseRate; // Time interval (in seconds) to release a ticket
    private TicketPoolService ticketPoolService; // Shared resource for managing tickets

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            Ticket ticket = new Ticket("Event " + (i + 1), new BigDecimal("1000"));
            synchronized (ticketPoolService) {
                while (ticketPoolService.getTickets().size() >= ticketPoolService.getMaxTicketCapacity()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " is waiting for space in the ticket pool.");
                        ticketPoolService.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
                ticketPoolService.addTicket(ticket);
                System.out.println(Thread.currentThread().getName() + " added ticket: " + ticket);
                ticketPoolService.notifyAll();
            }

            try {
                Thread.sleep(ticketReleaseRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
