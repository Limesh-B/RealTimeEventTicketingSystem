package lk.limesh.ticketingapp.threads;

import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.model.Ticket;
import lombok.AllArgsConstructor;

@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments
public class Customer implements Runnable {
    private TicketPoolController ticketPool;
    private int customerRetrievalRate; // Time interval (in seconds) between ticket retrievals
    private int quantity; // Number of tickets the customer wants to retrieve/buy

    // The run method is executed when then customer thread starts
    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            Ticket ticket = ticketPool.buyTicket();
            try {
                Thread.sleep(customerRetrievalRate * 250L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

