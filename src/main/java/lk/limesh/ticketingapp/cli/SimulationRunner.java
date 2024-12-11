package lk.limesh.ticketingapp.cli;

import lk.limesh.ticketingapp.controller.ConfigurationController;
import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.threads.Customer;
import lk.limesh.ticketingapp.threads.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulationRunner {

    private final Logger logger = LoggerFactory.getLogger(SimulationRunner.class);
    private static TicketPoolController ticketPool;
    private static ConfigurationController configuration;
    private Thread[] vendorThreads;
    private Thread[] customerThreads;

    public SimulationRunner(TicketPoolController ticketPool, ConfigurationController configuration) {
        this.ticketPool = ticketPool;
        this.configuration = configuration;
    }

    public void runSimulation() {
        Vendor[] vendors = new Vendor[10];
        vendorThreads = new Thread[10];  // Array to hold vendor threads
        for (int i = 0; i < vendors.length; i++) { // Loop to generate vendors
            vendors[i] = new Vendor(configuration.getTotalTickets(), configuration.getTicketReleaseRate(), ticketPool);
            vendorThreads[i] = new Thread(vendors[i], "Vendor ID : " + i);
            vendorThreads[i].start();
        }

        Customer[] customers = new Customer[10];
        customerThreads = new Thread[10];  // Array to hold customer threads
        for (int i = 0; i < customers.length; i++) { // Loop to generate customers
            customers[i] = new Customer(ticketPool, configuration.getCustomerRetrievalRate(), configuration.getTotalTickets());
            customerThreads[i] = new Thread(customers[i], "Customer ID : " + i);
            customerThreads[i].start();
        }
        logger.info("Simulation started");
    }

    public void stopSimulation() {
        for (Thread vendorThread : vendorThreads) {
            if (vendorThread != null && vendorThread.isAlive()) {
                vendorThread.interrupt();  // Interrupt the vendor thread to stop it
            }
        }

        for (Thread customerThread : customerThreads) {
            if (customerThread != null && customerThread.isAlive()) {
                customerThread.interrupt();  // Interrupt the customer thread to stop it
            }
        }


    }
}
