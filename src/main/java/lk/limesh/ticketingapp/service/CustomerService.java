package lk.limesh.ticketingapp.service;
import lk.limesh.ticketingapp.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final TicketPoolService ticketPoolService;

    public CustomerService(TicketPoolService ticketPoolService) {
        this.ticketPoolService = ticketPoolService;
    }

    /**
     * Simulates a customer buying tickets from the pool.
     * @param quantity Number of tickets to buy.
     * @param retrievalRate Time interval (in seconds) between ticket retrievals.
     */
    public void buyTickets(int quantity, int retrievalRate) {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPoolService.buyTicket();
            logger.info("Customer bought ticket: {}", ticket);

            try {
                Thread.sleep(retrievalRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while buying tickets", e);
            }
        }
    }
}

