package lk.limesh.ticketingapp;

import lk.limesh.ticketingapp.cli.SimulationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TicketingAppApplication {

	public static void main(String[] args) {
		// Start the Spring application context
		ConfigurableApplicationContext context = SpringApplication.run(TicketingAppApplication.class, args);

		// Retrieve the SimulationRunner bean and start the simulation
		SimulationRunner simulationRunner = context.getBean(SimulationRunner.class);
		simulationRunner.runSimulation();
	}

}
