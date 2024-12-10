package lk.limesh.ticketingapp.model;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Configuration {

    private int maxTicketCapacity;
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;

    @Override
    public String toString() {
        return "Configuration \n===============\n\nmaxTicketCapacity=" +
                maxTicketCapacity + "\ntotalTickets=" + totalTickets +
                "\nticketReleaseRate=" + ticketReleaseRate + "\ncustomerRetrievalRate=" +
                customerRetrievalRate;
    }
}