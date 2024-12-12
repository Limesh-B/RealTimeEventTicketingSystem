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
        Vendor[] vendors = new Vendor[10];  // Array to hold Vendor objects
        vendorThreads = new Thread[10];  // Array to hold Vendor threads
        for (int i = 0; i < vendors.length; i++) {
            // Initialize each Vendor with configuration values and ticket pool
            vendors[i] = new Vendor(configuration.getTotalTickets(), configuration.getTicketReleaseRate(), ticketPool);
            // Create a new thread for each vendor and start it
            vendorThreads[i] = new Thread(vendors[i], "Vendor ID : " + i);
            vendorThreads[i].start();
            logger.info("Vendor thread " + vendorThreads[i].getName() + " started");
        }

        Customer[] customers = new Customer[10];  // Array to hold Customer objects
        customerThreads = new Thread[10];  // Array to hold Customer threads
        for (int i = 0; i < customers.length; i++) {
            // Initialize each Customer with configuration values and ticket pool
            customers[i] = new Customer(ticketPool, configuration.getCustomerRetrievalRate(), configuration.getTotalTickets());
            // Create a new thread for each customer and start it
            customerThreads[i] = new Thread(customers[i], "Customer ID : " + i);
            customerThreads[i].start();
            logger.info("Customer thread " + customerThreads[i].getName() + " started");
        }
        logger.info("Simulation started with 10 vendor and 10 customer threads");
    }

    /**
     * Method to stop the simulation by interrupting all running vendor and customer threads.
     * This will stop their execution and end the simulation.
     */
    public void stopSimulation() {
        for (Thread vendorThread : vendorThreads) {
            if (vendorThread != null && vendorThread.isAlive()) {
                // Interrupt the vendor thread to stop it
                vendorThread.interrupt();
                logger.info("Vendor thread " + vendorThread.getName() + " interrupted");
            }
        }

        for (Thread customerThread : customerThreads) {
            if (customerThread != null && customerThread.isAlive()) {
                // Interrupt the customer thread to stop it
                customerThread.interrupt();
                logger.info("Customer thread " + customerThread.getName() + " interrupted");
            }
        }
    }
}
