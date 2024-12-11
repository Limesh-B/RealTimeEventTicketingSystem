package lk.limesh.ticketingapp.controller;

import lk.limesh.ticketingapp.model.Configuration;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lk.limesh.ticketingapp.service.ConfigurationService;
/**
 * ConfigurationController handles the configuration settings for the event ticketing system.
 */
@RestController
@RequestMapping("/api/config")
public class ConfigurationController {

    /**
     * The ConfigurationService instance that provides the logic for managing the system's configuration.
     */
    private final ConfigurationService configurationService;

    /**
     * Constructor for ConfigurationController.
     * Initializes the controller with a ConfigurationService instance with the use of dependency injection
     *
     * @param configurationService the ConfigurationService to manage ticketing system settings
     */
    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
    //! Comment these
    @PostMapping("/add-config")
    public ResponseEntity<?> addConfig(@RequestBody Configuration configuration) {
        this.configurationService.addConfiguration(configuration);
        return ResponseEntity.ok(configuration);
    }

    @GetMapping("/get-config")
    public ResponseEntity<Configuration> getConfig() {
        Configuration config= this.configurationService.sendConfiguration();
        return ResponseEntity.ok(config);
    }

    /**
     * Retrieves the current maximum ticket capacity from the configuration service.
     *
     * @return the maximum ticket capacity in the system
     */
    public int getMaxTicketCapacity() {
        return configurationService.getMaxTicketCapacity();

    }

    /**
     * Sets the maximum ticket capacity in the ticketing system.
     * This value determines the total number of tickets that can be held in the ticket pool at any given moment.
     *
     * @param maxTicketCapacity the maximum number of tickets
     */
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.configurationService.setMaxTicketCapacity(maxTicketCapacity);
    }

    /**
     * Retrieves the current total number of tickets per vendor.
     *
     * @return the total number of tickets available in the system
     */
    public int getTotalTickets() {
        return configurationService.getTotalTickets();
    }

    /**
     * Sets the total number of tickets available per vendor.
     *
     * @param totalTickets the total number of tickets
     */
    public void setTotalTickets(int totalTickets) {
        configurationService.setTotalTickets(totalTickets);
    }

    /**
     * Retrieves the current ticket release rate from the configuration service.
     * The ticket release rate controls how fast tickets are made available in the system.
     *
     * @return the rate at which tickets are released
     */
    public int getTicketReleaseRate() {
        return configurationService.getTicketReleaseRate();
    }

    /**
     * Sets the ticket release rate, which controls how fast tickets are released into the pool.
     *
     * @param ticketReleaseRate the rate at which tickets are released
     */
    public void setTicketReleaseRate(int ticketReleaseRate) {
        configurationService.setTicketReleaseRate(ticketReleaseRate);
    }

    /**
     * Retrieves the current customer retrieval rate from the configuration service.
     * The customer retrieval rate controls how quickly customers retrieve tickets from the pool.
     *
     * @return the rate at which customers retrieve tickets
     */
    public int getCustomerRetrievalRate() {
        return configurationService.getCustomerRetrievalRate();
    }

    /**
     * Sets the customer retrieval rate, which controls how quickly customers retrieve tickets from the pool.
     *
     * @param customerRetrievalRate the rate at which customers retrieve tickets
     */
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        configurationService.setCustomerRetrievalRate(customerRetrievalRate);
    }

    public void viewConfig() {
        configurationService.viewConfig();
    }

    public void saveConfiguration() {
        configurationService.saveToFile();
    }

    public boolean loadConfiguration() {
        if (configurationService.loadFromFile()) {
            return true;
        } else {
            return false;
        }
    }
}
