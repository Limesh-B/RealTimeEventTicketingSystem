package lk.limesh.ticketingapp.cli;

import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.threads.Customer;
import lk.limesh.ticketingapp.threads.Vendor;
import org.springframework.stereotype.Component;

@Component
public class SimulationRunner {

    private final TicketPoolController ticketPoolController;

    public SimulationRunner(TicketPoolController ticketPoolController) {
        this.ticketPoolController = ticketPoolController;
    }

    /**
     * Simulates the ticket pool operations with vendors and customers.
     */
    public void runSimulation() {
        Vendor[] vendors = new Vendor[5]; // Creating an array of vendors
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(10, 1, ticketPoolController);
            Thread vendorThread = new Thread(vendors[i], "Vendor-" + i);
            vendorThread.start();
        }

        Customer[] customers = new Customer[5]; // Creating an array of customers
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPoolController,1, 10);
            Thread customerThread = new Thread(customers[i], "Customer-" + i);
            customerThread.start();
        }
    }
}
