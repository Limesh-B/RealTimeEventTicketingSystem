package lk.limesh.ticketingapp.service;

import lk.limesh.ticketingapp.Repository.TicketRepository;
import lk.limesh.ticketingapp.model.Ticket;
import lk.limesh.ticketingapp.model.TicketPool;
import lk.limesh.ticketingapp.controller.ConfigurationController;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

@Service
@Getter  // Lombok annotation to generate getter methods for all fields
public class TicketPoolService {

    private static final Logger logger = LoggerFactory.getLogger(TicketPoolService.class);

    private final TicketRepository ticketRepository;  // The ticket pool object that manages all tickets.
    private int maxTicketsCapacity;  // Maximum capacity of the ticket pool.
    private int ticketsSold = 0;  // Number of tickets sold (added to the pool).
    private int ticketsBought = 0;  // Number of tickets bought (removed from the pool).

    /**
     * Constructor for TicketPoolService.
     * This constructor uses Dependency Injection to provide an instance of ConfigurationController.
     *
     * @param configurationController the ConfigurationController for fetching system configuration.
     */
    public TicketPoolService(ConfigurationController configurationController, TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.maxTicketsCapacity = configurationController.getMaxTicketCapacity();
    }

    /**
     * Adds a ticket to the ticket pool.
     * Synchronized to handle concurrent access.
     * @param ticket The ticket to add to the pool.
     */
    public synchronized void addTicket(Ticket ticket) {
        long currentTicketCount = ticketRepository.count();
        while (currentTicketCount >= this.maxTicketsCapacity) { // change
            try {
                logger.info("Waiting for space in the ticket pool...");
                wait();
            } catch (InterruptedException e) {
                logger.error("Thread interrupted while adding ticket");
            }
        }
        ticketsSold++;
        ticketRepository.save(ticket);
        logger.info("Added by: " + Thread.currentThread().getName() +  " Added ticket: " + ticket);
        notifyAll(); // Notify waiting threads
    }

    /**
     * Retrieves and removes the next available ticket from the pool.
     * Synchronized to handle concurrent access.
     * @return The next ticket, or null if none are available.
     */
    public synchronized Ticket buyTicket() {
        List<Ticket> tickets = ticketRepository.findAll();
        while (tickets.isEmpty()) {
            try {
                logger.info("Waiting for tickets to be added...");
                wait();
            } catch (InterruptedException e) {
                logger.error("Thread interrupted while buying ticket");
            }
        }
        ticketsBought++;
        Ticket ticket = tickets.get(tickets.size()-1);  // Remove and retrieve the ticket from the pool
        ticketRepository.delete(ticket);
        logger.info("Bought by: " + Thread.currentThread().getName() +  " Bought ticket: " + ticket);
        notifyAll(); // Notify waiting threads
        return ticket;
    }

    /**
     * Method to get all available tickets in the ticket pool
     *
     * @return queue of tickets
     */
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /**
     * Retrieves the total number of tickets available in the pool.
     *
     * @return The number of tickets in the pool.
     */
    public int getTotalTickets() {
        return this.getAllTickets().size();
    }

//    public int getTicketsBought() {return ticketsBought;}
}