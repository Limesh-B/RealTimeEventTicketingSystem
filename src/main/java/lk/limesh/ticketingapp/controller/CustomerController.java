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
     * API endpoint to simulate customer ticket purchase.
     * @param quantity Number of tickets to buy.
     * @param retrievalRate Time interval (in seconds) between ticket retrievals.
     */
    @PostMapping("/buy-tickets")
    public void buyTickets(@RequestParam int quantity, @RequestParam int retrievalRate) {
        logger.info("Customer ticket purchase initiated: quantity={}, retrievalRate={}", quantity, retrievalRate);
        new Thread(() -> customerService.buyTickets(quantity, retrievalRate)).start();
    }
}
