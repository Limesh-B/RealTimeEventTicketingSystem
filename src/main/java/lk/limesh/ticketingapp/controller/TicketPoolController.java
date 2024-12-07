package lk.limesh.ticketingapp.controller;

import lk.limesh.ticketingapp.model.Ticket;
import lk.limesh.ticketingapp.service.TicketPoolService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;
import java.util.Queue;

@RestController
@RequestMapping("/api/v1/ticket-pool")  // Endpoint for ticket pool
public class TicketPoolController {

    private static final Logger logger = LoggerFactory.getLogger(TicketPoolController.class);
    private final TicketPoolService ticketPoolService;

    /**
     * Constructor for dependency injection of TicketPoolService.
     * @param ticketPoolService: instance/object of TicketPoolService.
     */
    public TicketPoolController(TicketPoolService ticketPoolService) {
        this.ticketPoolService = ticketPoolService;
    }

    /**
     * Endpoint to add tickets to the ticket pool. Synchronized to handle concurrent access.
     * @param ticket: Ticket object that will be created after deserializing the JSON data from the request body
     *              using @RequestBody.
     */
    @PostMapping("/add-ticket")
    public void addTicket(@RequestBody Ticket ticket) {
        synchronized (ticketPoolService) {
            while (ticketPoolService.getTickets().size() >= ticketPoolService.getMaxTicketCapacity()) {
                try {
                    logger.info("Waiting for space in the ticket pool...");
                    ticketPoolService.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread interrupted while waiting: " + e.getMessage());
                }
            }
            ticketPoolService.addTicket(ticket);
            ticketPoolService.notifyAll();  // Notify all the waiting threads
            logger.info("Ticket added by thread {}: {}", Thread.currentThread().getName(), ticket);
        }
    }

    /**
     * Endpoint to buy a ticket from the ticket pool. Synchronized to handle concurrent access.
     * @return A ticket object.
     */
    @GetMapping("/buy-ticket")
    public Ticket buyTicket() {
        synchronized (ticketPoolService) {
            while (ticketPoolService.getTickets().isEmpty()) {
                try {
                    logger.info("Waiting for tickets to be added...");
                    ticketPoolService.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread interrupted while waiting: " + e.getMessage());
                }
            }
            Ticket ticket = ticketPoolService.getTicket();
            ticketPoolService.notifyAll();  // Notify all the waiting threads
            logger.info("Ticket bought by thread {}: {}", Thread.currentThread().getName(), ticket);
            return ticket;
        }
    }

    /**
     * Endpoint to retrieve all the tickets from the ticket pool.
     * @return A queue of all tickets currently in the ticket pool.
     */
    @GetMapping("/get-all-tickets")
    public Queue<Ticket> getAllTicket() {
        return ticketPoolService.getTickets();
    }
}
