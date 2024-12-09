package lk.limesh.ticketingapp.threads;

import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.model.Ticket;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Customer implements Runnable {
    private final TicketPoolController ticketPool;
    private int customerRetrievalRate; // Time interval (in seconds) between ticket retrievals
    private int quantity; // Number of tickets the customer wants to retrieve

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.buyTicket();
            try {
                Thread.sleep(customerRetrievalRate * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}

