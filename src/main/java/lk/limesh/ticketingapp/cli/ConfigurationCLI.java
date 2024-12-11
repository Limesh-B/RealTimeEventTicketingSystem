package lk.limesh.ticketingapp.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lk.limesh.ticketingapp.controller.ConfigurationController;
import java.util.Scanner;
import java.util.function.Consumer;

public class ConfigurationCLI {

    private final Logger logger = LoggerFactory.getLogger(ConfigurationCLI.class);// Initializes the logger
    private final ConfigurationController configurationController;
    Scanner userInput = new Scanner(System.in);

    /**
     * Constructor for Configuration CLI
     *
     * @param configurationController Dependency injected configuration controller
     */
    public ConfigurationCLI(ConfigurationController configurationController) {
        this.configurationController = configurationController;
    }

    /**
     * Method to run config CLI
     */
    public void run() {
        if (configurationController.loadConfiguration()) {
            while (true) {
                System.out.println();
                configurationController.viewConfig();
                System.out.println();
                System.out.print("Would you like to use the above saved config? (y/n): ");
                String input = userInput.nextLine();

                if (input.equalsIgnoreCase("y")) {
                    break;
                } else if (input.equalsIgnoreCase("n")) {
                    prompt();
                    configurationController.saveConfiguration();
                    break;
                } else {
                    System.out.println("\nInvalid input. Try again.");
                    System.out.print(">>> Press Enter to continue... <<<");
                    userInput.nextLine();
                }
            }
        } else {
            prompt();
        }
    }

    /**
     * Method to prompt the user to configure the system
     */
    private void prompt() {
        promptForInput("Enter Maximum Ticket Capacity: ", configurationController::setMaxTicketCapacity/*Is a method reference, it refers to a method without invoking it*/, false);
        promptForInput("Enter Total Tickets per vendor: ", configurationController::setTotalTickets, true);
        promptForInput("Enter Ticket Release Rate: ", configurationController::setTicketReleaseRate, false);
        promptForInput("Enter Customer Retrieval Rate: ", configurationController::setCustomerRetrievalRate, false);

        logger.info("\nConfiguration Added Successfully!");
        System.out.print("\n>>> Press Enter to continue... <<<");
        userInput.nextLine();
        configurationController.saveConfiguration();
    }

    /**
     * Method to get userInput and validate the input data
     *
     * @param promptMessage : The message that needs to be displayed
     * @param setterMethod  : Set method of the relevant input of configuration
     * @param flag          : Flag variable to access specific method to validate total ticket amount
     */
    private void promptForInput(String promptMessage, Consumer<Integer> setterMethod/*Allows to send a method as a parameter*/, boolean flag) {
        // Loops until user provides correct input
        while (true) {
            // Prompts user
            System.out.print(promptMessage);
            if (userInput.hasNextInt()) {
                int number = userInput.nextInt();
                userInput.nextLine(); // Consume the newline character
                // flag to check of the incoming value is "Total Ticket"
                if (flag) {
                    // Validate valid input range
                    if (inputValidation(number)) {
                        if (inputValidation(number, configurationController.getMaxTicketCapacity())) {
                            // used to access the method passed as a parameter
                            setterMethod.accept(number);
                            return;
                        } else {
                            System.out.println("\nEnter number less than max ticket capacity.\n");
                        }
                    } else {
                        System.out.println("\nEnter a positive integer.\n");
                    }
                } else {
                    if (inputValidation(number)) {
                        setterMethod.accept(number);
                        return;
                    } else {
                        System.out.println("\nEnter a positive integer.\n");
                    }
                }
            } else {
                System.out.println("\nEnter an integer.\n");
                userInput.nextLine(); // Consume the invalid input
            }
        }
    }

    /**
     * Method to validate accepted range of user input
     *
     * @param userInput : the value the user enters
     * @return : boolean value indicating if the input is correct or wrong | true: correct, false: wrong
     */
    private boolean inputValidation(int userInput) {
        if (userInput > 0) {
            return true;
        }
        return false;
    }

    /**
     * Method to specifically validate total ticket value
     *
     * @param totalTickets      : User input for total number of tickets per vendor
     * @param maxTicketCapacity : Maximum ticket capacity of the ticket pool
     * @return : boolean value indicating if the input is correct or wrong | true: correct, false: wrong
     * <p>
     * Total number of tickets should be less than maxTicketCapacity
     */
    private boolean inputValidation(int totalTickets, int maxTicketCapacity) {
        if (maxTicketCapacity >= totalTickets) {
            return true;
        }
        return false;
    }
}
