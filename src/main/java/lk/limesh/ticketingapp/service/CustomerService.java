package lk.limesh.ticketingapp.service;
import lk.limesh.ticketingapp.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final TicketPoolService ticketPoolService;

    /**
     * Simulates a customer buying tickets with a specified retrieval rate.
     * @param customerRetrievalRate Time interval (in seconds) between ticket retrievals.
     * @param quantity Number of tickets the customer wants to retrieve.
     */
    public void buyTickets(int customerRetrievalRate, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPoolService.buyTicket();
            logger.info("Customer bought ticket: {}", ticket);
            try {
                Thread.sleep(customerRetrievalRate * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted while buying tickets", e);
            }
        }
    }
}

