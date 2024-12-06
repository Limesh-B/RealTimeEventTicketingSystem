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
    private TicketPoolService ticketPoolService;

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
    public synchronized void addTicket(@RequestBody Ticket ticket) {
        while (ticketPoolService.getTickets().size() >= ticketPoolService.getMaxTicketCapacity()) {
            try {
                wait();
                logger.info("Waiting for space in ticket pool...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        ticketPoolService.addTicket(ticket);
        notifyAll();  // Notify all the waiting threads
        logger.info("Ticket added by: " + Thread.currentThread().getName() + " Added ticket: " + ticket);
    }

    /**
     * Endpoint to buy a ticket from the ticket pool. Synchronized to handle concurrent access.
     * @return A ticket object.
     */
    @GetMapping("/buy-ticket")
    public synchronized Ticket buyTicket() {
        while (ticketPoolService.getTickets().isEmpty()) {
            try {
                wait();
                logger.info("Waiting for tickets to be added...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        logger.info("Ticket bought by: " + Thread.currentThread().getName() +
                " Bought ticket: " + ticketPoolService.getTickets().peek());
        notifyAll();  // Notify all the waiting threads
        return ticketPoolService.getTicket();
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
