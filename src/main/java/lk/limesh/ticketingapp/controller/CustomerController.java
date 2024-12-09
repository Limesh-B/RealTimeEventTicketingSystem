package lk.limesh.ticketingapp.controller;

import lk.limesh.ticketingapp.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Endpoint to simulate a customer buying tickets.
     * @param customerRetrievalRate Time interval (in seconds) between ticket retrievals.
     * @param quantity Number of tickets the customer wants to retrieve.
     */
    @PostMapping("/buy-tickets")
    public void buyTickets(@RequestParam int customerRetrievalRate, @RequestParam int quantity) {
        logger.info("Customer request to buy {} tickets with a retrieval rate of {} seconds", quantity, customerRetrievalRate);
        new Thread(() -> customerService.buyTickets(customerRetrievalRate, quantity)).start();
    }
}
