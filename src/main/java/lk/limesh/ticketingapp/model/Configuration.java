package lk.limesh.ticketingapp.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@Getter // Lombok annotation to generate getter methods for all fields
@Setter  // Lombok annotation to generate setter methods for all fields
public class Configuration {

    // @JsonProperty annotation specifies that this field should be serialized/deserialized in JSON
    @JsonProperty
    private int maxTicketCapacity;
    @JsonProperty
    private int totalTickets;
    @JsonProperty
    private int ticketReleaseRate;
    @JsonProperty
    private int customerRetrievalRate;

    // toString method to represent the Configuration object as a formatted string.
    @Override
    public String toString() {
        return "\nConfiguration \n===============\n\nmaxTicketCapacity=" +
                maxTicketCapacity + "\ntotalTickets=" + totalTickets +
                "\nticketReleaseRate=" + ticketReleaseRate + "\ncustomerRetrievalRate=" +
                customerRetrievalRate;
    }
}