package lk.limesh.ticketingapp.cli;

import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.model.Customer;
import lk.limesh.ticketingapp.model.Vendor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SimulationRunner {

    private static TicketPoolController ticketpool;

    public SimulationRunner(TicketPoolController ticketpool) {
        this.ticketpool = ticketpool;
    }

    @Bean
    public void runSimulation() {
        Vendor[] vendors = new Vendor[10]; // Creating an array of vendors
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(20,5,ticketpool);
            Thread vendorThread = new Thread(vendors[i], "Vendor ID : " + i);
            vendorThread.start();
        }

        Customer[] customers = new Customer[10]; // Creating an array of customers
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketpool, 6, 5);
            Thread customerThread = new Thread(customers[i], "Customer ID : " + i);
            customerThread.start();
        }
    }
}
