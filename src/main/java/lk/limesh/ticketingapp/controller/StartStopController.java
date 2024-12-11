package lk.limesh.ticketingapp.controller;

import lk.limesh.ticketingapp.cli.SimulationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/control")
public class StartStopController {

    private final SimulationRunner simulationRunner;

    public StartStopController(TicketPoolController ticketPool, ConfigurationController configuration) {
        simulationRunner = new SimulationRunner(ticketPool, configuration);
    }

    @GetMapping("/start")
    public ResponseEntity<String> start() {
        System.out.println("System started.");
        simulationRunner.runSimulation();
        return ResponseEntity.ok("System started.");
    }

    @GetMapping("/stop")
    public ResponseEntity<String> stop() {
        System.out.println("System stopped.");
        simulationRunner.stopSimulation();
        return ResponseEntity.ok("System stopped.");
    }
}
