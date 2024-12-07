package lk.limesh.ticketingapp.threads;

import lk.limesh.ticketingapp.model.Ticket;
import lk.limesh.ticketingapp.model.TicketPool;
import lk.limesh.ticketingapp.service.TicketPoolService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Customer implements Runnable {
    private int customerRetrievalRate; // Time interval (in seconds) between ticket retrievals
    private int quantity; // Number of tickets the customer wants to retrieve
    private TicketPoolService ticketPoolService; // Shared resource for managing tickets

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket;
            synchronized (ticketPoolService) {
                while (ticketPoolService.getTickets().isEmpty()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " is waiting for tickets to be available.");
                        ticketPoolService.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
                ticket = ticketPoolService.getTicket();
                System.out.println(Thread.currentThread().getName() + " bought ticket: " + ticket);
                ticketPoolService.notifyAll();
            }

            try {
                Thread.sleep(customerRetrievalRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}

