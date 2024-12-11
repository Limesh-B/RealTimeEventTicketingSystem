package lk.limesh.ticketingapp.cli;

import lk.limesh.ticketingapp.controller.ConfigurationController;
import lk.limesh.ticketingapp.controller.TicketPoolController;
import lk.limesh.ticketingapp.threads.Customer;
import lk.limesh.ticketingapp.threads.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The SimulationRunner class is responsible for managing the simulation of vendors and customers interacting with the ticket pool.
 * It initializes and starts vendor and customer threads, which simulate ticket sales and retrieval.
 */
public class SimulationRunner {

    // Logger to log simulation events
    private final Logger logger = LoggerFactory.getLogger(SimulationRunner.class);
    // Controllers for ticket pool and configuration
    private static TicketPoolController ticketPool;
    private static ConfigurationController configuration;

    // Arrays to hold vendor and customer threads
    private Thread[] vendorThreads;
    private Thread[] customerThreads;

    /**
     * Constructor for SimulationRunner.
     * This constructor accepts the dependencies (TicketPoolController and ConfigurationController)
     * and injects them into the SimulationRunner class.
     *
     * @param ticketPool The TicketPoolController instance that manages the ticket pool
     * @param configuration The ConfigurationController instance that provides system configurations
     */
    public SimulationRunner(TicketPoolController ticketPool, ConfigurationController configuration) {
        // Inject the TicketPoolController and ConfigurationController instances
        this.ticketPool = ticketPool;
        this.configuration = configuration;
    }

    /**
     * Method to start the simulation by creating and running vendor and customer threads.
     * It generates 10 vendors and 10 customers, each of which is assigned a separate thread to run concurrently.
     */
    public void runSimulation() {
        // Create an array to hold 10 Vendor objects
        Vendor[] vendors = new Vendor[10];
        vendorThreads = new Thread[10];  // Array to hold vendor threads
        // Loop through the vendor array, creating and starting a thread for each vendor
        for (int i = 0; i < vendors.length; i++) {
            // Initialize each Vendor with configuration values and ticket pool
            vendors[i] = new Vendor(configuration.getTotalTickets(), configuration.getTicketReleaseRate(), ticketPool);
            vendorThreads[i] = new Thread(vendors[i], "Vendor ID : " + i);
            vendorThreads[i].start(); // Start the Vendor threads
        }

        // Create an array to hold 10 Customer objects
        Customer[] customers = new Customer[10];
        customerThreads = new Thread[10];  // Array to hold customer threads
        // Loop through the customer array, creating and starting a thread for each customer
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, configuration.getCustomerRetrievalRate(), configuration.getTotalTickets());
            customerThreads[i] = new Thread(customers[i], "Customer ID : " + i);
            customerThreads[i].start(); // Start the Customer threads
        }
        logger.info("Simulation started");
    }

    /**
     * Method to stop the simulation by interrupting all running vendor and customer threads.
     * This will stop their execution and end the simulation.
     */
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
