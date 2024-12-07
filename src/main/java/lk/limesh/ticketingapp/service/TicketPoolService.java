package lk.limesh.ticketingapp.service;

import lk.limesh.ticketingapp.model.Ticket;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
@Getter
public class TicketPoolService {

    private final Queue<Ticket> tickets;  // Queue to store the Tickets
    private final int maxTicketCapacity; // Maximum ticket capacity that can be in the TicketPool

    /**
     * Default constructor for TicketPoolService class.
     * Initializes the tickets Queue and sets the maxTicketCapacity to a default value.
     */
    public TicketPoolService() {
        this(10); // Default max capacity
    }

    /**
     * Constructor to initialize TicketPoolService with a custom maximum capacity.
     * @param maxTicketCapacity The maximum capacity of the ticket pool.
     */
    public TicketPoolService(int maxTicketCapacity) {
        this.tickets = new LinkedList<>();
        if (maxTicketCapacity <= 0) {
            throw new IllegalArgumentException("Max ticket capacity must be greater than zero.");
        }
        this.maxTicketCapacity = maxTicketCapacity;
    }

    /**
     * Adds a ticket to the ticket pool if capacity is not exceeded.
     * @param ticket The ticket to be added to the pool.
     * @throws IllegalStateException if the ticket pool is full.
     */
    public synchronized void addTicket(Ticket ticket) {
        if (tickets.size() >= maxTicketCapacity) {
            throw new IllegalStateException("Ticket pool is full. Cannot add more tickets.");
        }
        tickets.add(ticket);
    }

    /**
     * Retrieves and removes a ticket from the ticket pool.
     * @return The next ticket in the ticket pool, or null if the ticket pool is empty.
     */
    public synchronized Ticket getTicket() {
        return tickets.poll();
    }
}
