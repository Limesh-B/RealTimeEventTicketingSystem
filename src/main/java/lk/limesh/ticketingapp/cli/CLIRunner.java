package lk.limesh.ticketingapp.cli;

import lk.limesh.ticketingapp.controller.ConfigurationController;
import lk.limesh.ticketingapp.controller.TicketPoolController;

/**
 * The CLIRunner class is used for running the Command-Line Interface (CLI)
 * of the Ticketing App. It initializes and orchestrates the configuration and simulation components.
 */
public class CLIRunner {

    // CLI component for managing application configuration
    private final ConfigurationCLI configCLI;
    // Component responsible for running ticket pool simulations
    private final SimulationRunner simulationRunner;

    /**
     * Constructs the CLIRunner and initializes its components with the provided controllers.
     *
     * @param configController The controller for managing configuration settings.
     * @param ticketPoolController The controller for managing ticket pools.
     */
    public CLIRunner(ConfigurationController configController, TicketPoolController ticketPoolController) {
        this.configCLI = new ConfigurationCLI(configController);
        this.simulationRunner = new SimulationRunner(ticketPoolController, configController);
    }

    /**
     * Runs the CLI by executing the configuration setup and starting the ticket pool simulation.
     */
    public void runCLI() {
        // Execute the configuration CLI
        configCLI.run();
        // Start the simulation process
        simulationRunner.runSimulation();
    }
}
