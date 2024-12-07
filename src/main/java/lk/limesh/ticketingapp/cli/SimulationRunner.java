package lk.limesh.ticketingapp.cli;

import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.service.TicketPoolService;
import lk.limesh.ticketingapp.threads.Customer;
import lk.limesh.ticketingapp.threads.Vendor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SimulationRunner {

    private final TicketPoolService ticketPoolService;

    public SimulationRunner(TicketPoolService ticketPoolService) {
        this.ticketPoolService = ticketPoolService;
    }

    public void runSimulation() {
        Vendor[] vendors = new Vendor[10]; // Creating an array of vendors
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(20, 5, ticketPoolService);
            Thread vendorThread = new Thread(vendors[i], "Vendor-" + i);
            vendorThread.start();
        }

        Customer[] customers = new Customer[10]; // Creating an array of customers
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(6, 5, ticketPoolService);
            Thread customerThread = new Thread(customers[i], "Customer-" + i);
            customerThread.start();
        }
    }
}
