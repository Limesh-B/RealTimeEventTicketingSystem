package lk.limesh.ticketingapp.model;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;

@AllArgsConstructor
@Getter
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;


}