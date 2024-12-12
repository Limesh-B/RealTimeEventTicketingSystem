package lk.limesh.ticketingapp.service;

import lk.limesh.ticketingapp.Repository.TicketRepository;
import lk.limesh.ticketingapp.model.Ticket;
import lk.limesh.ticketingapp.controller.ConfigurationController;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Getter  // Lombok annotation to generate getter methods for all fields
public class TicketPoolService {

    private static final Logger logger = LoggerFactory.getLogger(TicketPoolService.class);

    private final TicketRepository ticketRepository;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private int maxTicketsCapacity;  // Maximum capacity of the ticket pool.
    private int ticketsSold = 0;  // Number of tickets sold (added to the pool).
    private int ticketsBought = 0;  // Number of tickets bought (removed from the pool).

    /**
     * Constructor for TicketPoolService.
     * This constructor uses Dependency Injection to provide an instance of ConfigurationController.
     *
     * @param configurationController the ConfigurationController for fetching system configuration.
     * @param ticketRepository the TicketRepository for performing CRUD operations on tickets.
     */
    public TicketPoolService(ConfigurationController configurationController, TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.maxTicketsCapacity = configurationController.getMaxTicketCapacity();
    }

    /**
     * Adds a ticket to the ticket pool.
     * Uses locks to handle concurrent access.
     *
     * @param ticket The ticket to add to the pool.
     */
    public void addTicket(Ticket ticket) {
        lock.lock();
        try {
            while (ticketRepository.count() >= this.maxTicketsCapacity) {
                try {
                    logger.info("Waiting for space in the ticket pool...");
                    notFull.await();
                } catch (InterruptedException e) {
                    logger.error("Thread interrupted while adding ticket");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            ticketsSold++;
            ticketRepository.save(ticket);
            logger.info("Added by: " + Thread.currentThread().getName() + " Added ticket: " + ticket);
            notEmpty.signalAll(); // Notify waiting threads
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves and removes the next available ticket from the pool.
     * Uses locks to handle concurrent access.
     *
     * @return The next ticket, or null if none are available.
     */
    public Ticket buyTicket() {
        lock.lock();
        try {
            while (ticketRepository.count() == 0) {
                try {
                    logger.info("Waiting for tickets to be added...");
                    notEmpty.await();
                } catch (InterruptedException e) {
                    logger.error("Thread interrupted while buying ticket");
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            ticketsBought++;
            List<Ticket> tickets = ticketRepository.findAll();
            Ticket ticket = tickets.get(tickets.size() - 1);  // Retrieve the last ticket added to the pool
            ticketRepository.delete(ticket);
            logger.info("Bought by: " + Thread.currentThread().getName() + " Bought ticket: " + ticket);
            notFull.signalAll(); // Notify waiting threads
            return ticket;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets all available tickets in the ticket pool.
     *
     * @return List of tickets currently in the pool.
     */
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Retrieves the total number of tickets available in the pool.
     *
     * @return The number of tickets currently in the pool.
     */
    public int getTotalTickets() {
        return this.getAllTickets().size();
    }
}
