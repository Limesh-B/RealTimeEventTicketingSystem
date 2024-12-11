package lk.limesh.ticketingapp.model;


import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;


@Getter
public class TicketPool {
    private final Queue<Ticket> tickets;

    /**
     * Method to create a new ticket pool
     * @return: TicketPool instance
     */
    public static TicketPool createEmptyPool() {
        return new TicketPool(new LinkedList<>());
    }

    /**
     * Constructor for the ticket pool
     * @param tickets: Dependency injection of Queue with datatype ticket
     */
    private TicketPool(Queue<Ticket> tickets) {
        this.tickets = tickets;
    }

    /**
     * Method to add a ticket to ticket pool
     * @param ticket: Ticket added byb vendor | Flow: Controller => Service => Model
     */
    public synchronized void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    /**
     * Method to buy a ticket from ticket pool
     * @return ticket instance from ticket queue using poll method
     */
    public synchronized Ticket pollTicket() {
        return tickets.poll();
    }

}
