package lk.limesh.ticketingapp.service;
import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.threads.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final TicketPoolController ticketPool;

    public CustomerService(TicketPoolService ticketPoolService) {
        this.ticketPoolController = ticketPool;
    }

    public void buyTickets(int quantity, int retrievalRate) {
        Customer customer = new Customer(ticketPool, retrievalRate, quantity);
        Thread customerThread = new Thread(customer, "Customer-Thread");
        customerThread.start();
    }
}
