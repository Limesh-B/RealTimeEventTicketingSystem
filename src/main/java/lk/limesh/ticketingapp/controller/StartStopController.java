package lk.limesh.ticketingapp.controller;

import lk.limesh.ticketingapp.cli.SimulationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StartStopController is responsible for starting and stopping the ticketing simulation.
 * It has two API endpoints: one to start the simulation and one to stop it.
 */
@RestController
@RequestMapping("/api/control")  // Maps all requests starting with "/api/control" to this controller
public class StartStopController {

    /**
     * The SimulationRunner instance that handles the simulation logic, including starting and stopping the simulation.
     * It is injected via dependency injection.
     */
    private final SimulationRunner simulationRunner;

    /**
     * Constructor for StartStopController.
     * Initializes the controller with a SimulationRunner instance via Dependency Injection.
     *
     * @param ticketPool The TicketPoolController that manages the ticket pool for the simulation.
     * @param configuration The ConfigurationController that holds the configuration settings for the system.
     */
    public StartStopController(TicketPoolController ticketPool, ConfigurationController configuration) {
        // Initializes SimulationRunner using the injected controllers
        simulationRunner = new SimulationRunner(ticketPool, configuration);
    }

    /**
     * API endpoint to start the simulation.
     * This method starts the simulation by invoking the runSimulation method of the SimulationRunner.
     *
     * @return ResponseEntity with status OK and a message indicating that the system has started
     */
    @GetMapping("/start")
    public ResponseEntity<String> start() {
        System.out.println("System started.");
        simulationRunner.runSimulation();
        return ResponseEntity.ok("System started.");
    }

    /**
     * API endpoint to stop the simulation.
     * This method stops the simulation by invoking the stopSimulation method of the SimulationRunner.
     *
     * @return ResponseEntity with status OK and a message indicating that the system has stopped
     */
    @GetMapping("/stop")
    public ResponseEntity<String> stop() {
        System.out.println("System stopped.");
        simulationRunner.stopSimulation(); // Stops the simulation
        return ResponseEntity.ok("System stopped.");
    }
}
