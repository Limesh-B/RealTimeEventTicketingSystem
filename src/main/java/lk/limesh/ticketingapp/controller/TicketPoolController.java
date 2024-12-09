package lk.limesh.ticketingapp.controller;

import lk.limesh.ticketingapp.model.Ticket;
import lk.limesh.ticketingapp.service.TicketPoolService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;
import java.util.Queue;

@RestController
@RequestMapping("/api/ticket-pool")
public class TicketPoolController {

    private final Logger logger = LoggerFactory.getLogger(TicketPoolController.class);
    private TicketPoolService ticketPoolService;

    /**
     * Constructor for dependency injection of TicketPoolService.
     * @param ticketPoolService TicketPoolService instance.
     */
    public TicketPoolController(TicketPoolService ticketPoolService) {
        this.ticketPoolService = ticketPoolService;
    }

    /**
     * Adds a ticket to the pool.
     * @param ticket Ticket object deserialized from JSON request body.
     */
    @PostMapping("/add-ticket")
    public void addTicket(@RequestBody Ticket ticket) {
        ticketPoolService.addTicket(ticket);
        logger.info("Added by: " + Thread.currentThread().getName() +  " Added ticket: " + ticket);
    }

    /**
     * Buys a ticket from the pool.
     * @return The purchased ticket.
     */
    @GetMapping("/buy-ticket")
    public Ticket buyTicket() {
        Ticket ticket = ticketPoolService.buyTicket();
        logger.info("Bought by: " + Thread.currentThread().getName() +  " Bought ticket: " + ticket);
        return ticket;
    }

    /**
     * Retrieves all tickets currently in the pool.
     * @return A queue containing all tickets in the pool.
     */
    @GetMapping("/get-all-tickets")
    public Queue<Ticket> getAllTickets() {
        Queue<Ticket> tickets = ticketPoolService.getAllTickets();
        logger.info("Retrieved all tickets: " + tickets);
        return tickets;
    }
}
