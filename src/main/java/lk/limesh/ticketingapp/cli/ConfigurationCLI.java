package lk.limesh.ticketingapp.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lk.limesh.ticketingapp.controller.ConfigurationController;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * The ConfigurationCLI class provides a command-line interface for configuring the system settings.
 * It interacts with the ConfigurationController to load, view, and update system configurations.
 */
public class ConfigurationCLI {

    // Logger to log information and debugging messages
    private final Logger logger = LoggerFactory.getLogger(ConfigurationCLI.class);// Initializes the logger
    // Controller responsible for managing configurations
    private final ConfigurationController configurationController;
    // Scanner instance to capture user input
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
     * Runs the configuration CLI. Provides options to load, view, and update configurations.
     * Allows the user to either use saved configurations or create new ones.
     */
    public void run() {
        // Check if there is an existing configuration saved in the json file
        if (configurationController.loadConfiguration()) {
            while (true) {
                System.out.println();

                // Display the current configuration
                configurationController.viewConfig();
                System.out.println();
                // Prompt the user for decision on using saved configurations
                System.out.print("Would you like to use the above saved config? (y/n): ");
                String input = userInput.nextLine();

                if (input.equalsIgnoreCase("y")) {
                    break;
                } else if (input.equalsIgnoreCase("n")) {
                    // If user wants to update the configuration
                    prompt();
                    configurationController.saveConfiguration();
                    break;
                } else {
                    // Handle invalid input and prompt user to retry
                    System.out.println("\nInvalid input. Try again.");
                    System.out.print(">>> Press Enter to continue... <<<");
                    userInput.nextLine();
                }
            }
        } else {
            // If no saved configuration exists, prompt user to configure the system
            prompt();
        }
    }

    /**
     * Method to prompt the user to configure the system and validates the inputs.
     */
    private void prompt() {
        // Prompt and set each configuration value with validation
        promptForInput("Enter Maximum Ticket Capacity: ", configurationController::setMaxTicketCapacity/*Is a method reference, it refers to a method without invoking it*/);
        promptForInput("Enter Total Tickets per vendor: ", configurationController::setTotalTickets);
        promptForInput("Enter Ticket Release Rate: ", configurationController::setTicketReleaseRate);
        promptForInput("Enter Customer Retrieval Rate: ", configurationController::setCustomerRetrievalRate);

        logger.info("\nConfiguration Added Successfully!");
        System.out.print("\n>>> Press Enter to continue... <<<");
        userInput.nextLine();
        // Save the newly added configuration
        configurationController.saveConfiguration();
    }

    /**
     * Method to get userInput and validate the input data
     *
     * @param promptMessage : The message that needs to be displayed
     * @param setterMethod  : Set method of the relevant input of configuration
     */
    private void promptForInput(String promptMessage, Consumer<Integer> setterMethod/*Allows to send a method as a parameter*/) {
        // Loops until user provides correct input
        while (true) {
            // Prompts user
            System.out.print(promptMessage);
            if (userInput.hasNextInt()) {
                int number = userInput.nextInt();
                userInput.nextLine(); // Consume the newline character
                // Validate the input value
                if (inputValidation(number)) {
                    // If valid, set the configuration using the provided setter method
                    setterMethod.accept(number);
                    return;
                } else {
                    System.out.println("\nEnter a positive integer.\n");
                }
            } else {
                // Handle invalid (non-integer) input
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

}
