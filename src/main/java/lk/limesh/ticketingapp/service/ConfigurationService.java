package lk.limesh.ticketingapp.service;

import com.google.gson.Gson;
import lk.limesh.ticketingapp.model.Configuration;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ConfigurationService {
    public static Configuration loadFromFile(String filePath) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Configuration.class);
        }
    }

    public void saveToFile(String filePath) throws IOException {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        }
    }
}
