package lk.limesh.ticketingapp.service;

import lk.limesh.ticketingapp.model.Ticket;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
@Getter
public class TicketPoolService {

    private Queue<Ticket> tickets;  // Queue to store the Tickets
    private int maxTicketCapacity;  // Maximum ticket capacity that can be in the TicketPool

    /**
     * Default constructor for TicketPoolService class.
     * Initializes the tickets Queue and sets the maxTicketCapacity.
     */
    public TicketPoolService() {
        tickets = new LinkedList<Ticket>();
        maxTicketCapacity = 10;
    }

    /**
     * Adds a ticket to the ticket pool. (Used by vendor)
     * @param ticket: The ticket to be added to the pool.
     */
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    /**
     * Returns the ticket from the ticket pool. (Used by customer)
     * @return the next ticket in the ticket pool or null if the ticket pool is empty.
     */
    public Ticket getTicket() {
        return tickets.poll();
    }
}
