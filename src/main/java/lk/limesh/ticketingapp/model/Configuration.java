package lk.limesh.ticketingapp.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Configuration {

    @JsonProperty
    private int maxTicketCapacity;
    @JsonProperty
    private int totalTickets;
    @JsonProperty
    private int ticketReleaseRate;
    @JsonProperty
    private int customerRetrievalRate;

    @Override
    public String toString() {
        return "\nConfiguration \n===============\n\nmaxTicketCapacity=" +
                maxTicketCapacity + "\ntotalTickets=" + totalTickets +
                "\nticketReleaseRate=" + ticketReleaseRate + "\ncustomerRetrievalRate=" +
                customerRetrievalRate;
    }
}