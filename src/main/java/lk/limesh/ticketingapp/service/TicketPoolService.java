package lk.limesh.ticketingapp.service;

import lk.limesh.ticketingapp.model.Ticket;
import lk.limesh.ticketingapp.model.TicketPool;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Queue;

@Service
@Getter
public class TicketPoolService {

    private static final Logger logger = LoggerFactory.getLogger(TicketPoolService.class);

    private final TicketPool ticketPool;
    private final int maxTicketsCapacity;

    public TicketPoolService() {
        this.ticketPool = TicketPool.createEmptyPool(); // Initialize with an empty pool
        this.maxTicketsCapacity = 5;
    }

    /**
     * Adds a ticket to the ticket pool.
     * Synchronized to handle concurrent access.
     * @param ticket The ticket to add to the pool.
     */
    public synchronized void addTicket(Ticket ticket) {
        while (ticketPool.size() >= maxTicketsCapacity) {
            try {
                logger.info("Waiting for space in the ticket pool...");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while adding ticket", e);
            }
        }
        ticketPool.addTicket(ticket);
        logger.info("Added by: " + Thread.currentThread().getName() +  " Added ticket: " + ticket);
        notifyAll(); // Notify waiting threads
    }

    /**
     * Retrieves and removes the next available ticket from the pool.
     * Synchronized to handle concurrent access.
     * @return The next ticket, or null if none are available.
     */
    public synchronized Ticket buyTicket() {
        while (ticketPool.isEmpty()) {
            try {
                logger.info("Waiting for tickets to be added...");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread interrupted while buying ticket", e);
            }
        }
        Ticket ticket = ticketPool.pollTicket();
        logger.info("Bought by: " + Thread.currentThread().getName() +  " Bought ticket: " + ticket);
        notifyAll(); // Notify waiting threads
        return ticket;
    }

    /**
     * Method to get all available tickets in the ticket pool
     * @return queue of tickets
     */
    public Queue<Ticket> getAllTickets() {
        Queue<Ticket> tickets = ticketPool.getTickets();
        logger.info("Retrieved all tickets via controller: " + tickets);
        return tickets;
    }
}
