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

@Service
public class ConfigurationService {

    private final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);
    private final Gson gson;
    private Configuration configuration;

    @Value("${configuration.file.path}")
    private String filePath;  // Injected file path from application properties

    public ConfigurationService(Gson gson) {
        this.gson = gson;
    }

    public void addConfiguration(Configuration configuration) {
        this.configuration = configuration;
        saveToFile();
        logger.info("Configuration added through post request");
        viewConfig();
    }

    public Configuration sendConfiguration() {
        return configuration;
    }

    @PostConstruct
    private void initialize() {
        if (!loadFromFile()) {
            logger.warn("Configuration loading failed. A new configuration will be created.");
            createDefaultConfiguration();
            saveToFile();
        }
    }

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

    public void viewConfig() {
        logger.info(configuration.toString());
    }

    // Creates a default configuration
    private void createDefaultConfiguration() {
        this.configuration = new Configuration(500, 20, 2, 2);  // Example default values
    }

    // Load configuration from file
    public boolean loadFromFile() {
        try (Reader reader = new FileReader(filePath)) {
            this.configuration = gson.fromJson(reader, Configuration.class);
            return true;
        } catch (IOException | JsonSyntaxException e) {
            logger.error("Error loading configuration from file: " + e.getMessage());
            return false;
        }
    }

    // Save configuration to file
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
