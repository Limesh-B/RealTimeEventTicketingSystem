package lk.limesh.ticketingapp.cli;

public class Main {
    public static void main(String[] args) {
        // Creating a ticket pool with 10 as maximum capacity
        TicketPool ticketPool = new TicketPool(10);

        Vendor[] vendors = new Vendor[10]; // Creating array of vendors
        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(20, 5, ticketPool);
            Thread vendorThread = new Thread(vendors[i], "Vendor ID-" + i);
            vendorThread.start();
        }

        Customer[] customers = new Customer[10]; // Creating array of customers
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, 6, 5); // Retrieve tickets from the pool
            Thread customerThread = new Thread(customers[i], "Customer ID-" + i);
            customerThread.start();
        }
    }
}
