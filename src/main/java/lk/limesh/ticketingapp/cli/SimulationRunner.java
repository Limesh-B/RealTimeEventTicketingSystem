package lk.limesh.ticketingapp.cli;

import lk.limesh.ticketingapp.controller.ConfigurationController;
import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.threads.Customer;
import lk.limesh.ticketingapp.threads.Vendor;

public class SimulationRunner {

    private static TicketPoolController ticketPool;
    private static ConfigurationController configuration;

    public SimulationRunner(TicketPoolController ticketPool, ConfigurationController configuration) {
        this.ticketPool = ticketPool;
        this.configuration = configuration;
    }

    public void runSimulation() {
        Vendor[] vendors = new Vendor[10];
        for (int i = 0; i < vendors.length; i++) { // Loop to generate vendors
            vendors[i] = new Vendor(configuration.getTotalTickets(),configuration.getTicketReleaseRate(), ticketPool);
            Thread vendorthread = new Thread(vendors[i], "Vendor ID : " + i);
            vendorthread.start();
        }

        Customer[] customers = new Customer[10];
        for (int i = 0; i < customers.length; i++) { // Loop to generate customers
            customers[i] = new Customer(ticketPool, configuration.getCustomerRetrievalRate(), 5);
            Thread customerThread = new Thread(customers[i], "Customer ID : " + i);
            customerThread.start();
        }
    }
}
