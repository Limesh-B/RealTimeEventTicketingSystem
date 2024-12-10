package lk.limesh.ticketingapp;

import lk.limesh.ticketingapp.cli.CLIRunner;
import org.springframework.boot.SpringApplication;
import lk.limesh.ticketingapp.controller.ConfigurationController;
import lk.limesh.ticketingapp.controller.TicketPoolController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TicketingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner cli(ConfigurationController configurationController, TicketPoolController ticketPoolController) {
		return args -> {
			CLIRunner cliRunner = new CLIRunner(configurationController, ticketPoolController);
			cliRunner.runCLI();
		};
	}
}
