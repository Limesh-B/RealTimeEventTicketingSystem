package lk.limesh.ticketingapp.controller;

import lk.limesh.ticketingapp.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/buy-tickets")
    public void buyTickets(@RequestParam int quantity, @RequestParam int retrievalRate) {
        logger.info("Customer requested {} tickets with a retrieval rate of {} seconds.", quantity, retrievalRate);
        customerService.buyTickets(quantity, retrievalRate);
    }
}
