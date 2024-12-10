package lk.limesh.ticketingapp.cli;

import lk.limesh.ticketingapp.controller.ConfigurationController;
import lk.limesh.ticketingapp.controller.TicketPoolController;

public class CLIRunner {

    private final ConfigurationCLI configCLI;
    private final SimulationRunner simulationRunner;

    public CLIRunner(ConfigurationController configController, TicketPoolController ticketPoolController) {
        this.configCLI = new ConfigurationCLI(configController);
        this.simulationRunner = new SimulationRunner(ticketPoolController, configController);
    }

    public void runCLI() {
        configCLI.run();
        simulationRunner.runSimulation();
    }
}
