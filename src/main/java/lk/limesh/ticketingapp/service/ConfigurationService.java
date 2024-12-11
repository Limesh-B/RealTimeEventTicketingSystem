package lk.limesh.ticketingapp.service;

import com.google.gson.Gson;
import java.io.*;
import com.google.gson.JsonSyntaxException;
import jakarta.annotation.PostConstruct;
import lk.limesh.ticketingapp.model.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The ConfigurationService class contains the business logic for the Configuration.
 */
@Service
public class ConfigurationService {

    private final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);
    private final Gson gson;  // Gson instance for JSON processing
    private Configuration configuration;  // Holds the current configuration

    // Injected file path from application properties for reading and writing configuration
    @Value("${configuration.file.path}")
    private String filePath;  // Injected file path from application properties

    /**
     * Constructor for ConfigurationService.
     * Initializes the service with a Gson instance for JSON processing.
     *
     * @param gson the Gson instance used to serialize and deserialize configuration data
     */
    public ConfigurationService(Gson gson) {
        this.gson = gson;
    }

    /**
     * Adds a new configuration and saves it to a file.
     * This method is used to update the system's configuration with a new configuration object from the frontend.
     * After updating the configuration, it is saved to the file, and the configuration is viewed.
     *
     * @param configuration the new configuration to be added
     */
    public void addConfiguration(Configuration configuration) {
        this.configuration = configuration;
        saveToFile();
        logger.info("Configuration added through post request");
        viewConfig();
    }

    /**
     * Retrieves the current configuration.
     *
     * @return the current configuration object
     */
    public Configuration sendConfiguration() {
        return configuration;
    }

    /**
     * @PostConstruct means it will be executed automatically by Spring after the bean has been
     * constructed and its dependencies have been injected.
     * The method attempts to load the configuration from a file. If loading fails,
     * it creates a default configuration and saves it to the file.
     */
    @PostConstruct
    private void initialize() {
        if (!loadFromFile()) {
            logger.warn("Configuration loading failed. A new configuration will be created.");
            createDefaultConfiguration();
            saveToFile();
        }
    }

    // Getters and Setters for configuration properties

    public int getMaxTicketCapacity() {
        return configuration.getMaxTicketCapacity();
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        configuration.setMaxTicketCapacity(maxTicketCapacity);
    }

    public int getTotalTickets() {
        return configuration.getTotalTickets();
    }

    public void setTotalTickets(int totalTickets) {
        configuration.setTotalTickets(totalTickets);
    }

    public int getTicketReleaseRate() {
        return configuration.getTicketReleaseRate();
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        configuration.setTicketReleaseRate(ticketReleaseRate);
    }

    public int getCustomerRetrievalRate() {
        return configuration.getCustomerRetrievalRate();
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        configuration.setCustomerRetrievalRate(customerRetrievalRate);
    }

    /**
     * Logs the current configuration.
     * This method is used to log the details of the current configuration for debugging or monitoring.
     */
    public void viewConfig() {
        logger.info(configuration.toString());
    }

    // Creates a default configuration in cli
    private void createDefaultConfiguration() {
        this.configuration = new Configuration(500, 20, 2, 2);  // Example default values
    }

    /**
     * Loads the configuration from a json file.
     * This method reads the configuration file, parses it, and sets the configuration object.
     *
     * @return true if the configuration is loaded successfully, false otherwise
     */
    public boolean loadFromFile() {
        try (Reader reader = new FileReader(filePath)) {
            this.configuration = gson.fromJson(reader, Configuration.class);
            return true;
        } catch (IOException | JsonSyntaxException e) {
            logger.error("Error loading configuration from file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Saves the current configuration to a json file.
     * This method serializes the configuration object into a JSON format and writes it to the file.
     *
     * @return true if the configuration is saved successfully, false otherwise
     */
    public boolean saveToFile() {
        try {
            File file = new File(filePath);
            if (!file.exists() && !file.createNewFile()) {
                logger.error("Failed to create the configuration file.");
                return false;
            }
            try (Writer writer = new FileWriter(filePath)) {
                gson.toJson(this.configuration, writer);  // Save the Configuration object to file
                return true;
            }
        } catch (IOException e) {
            logger.error("Error saving configuration to file: " + e.getMessage());
            return false;
        }
    }
}
