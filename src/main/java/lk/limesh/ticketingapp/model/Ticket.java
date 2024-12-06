package lk.limesh.ticketingapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

// @Data annotation automatically generates boilerplate code for getters, setters and toString() method
@Data
public class Ticket {
    private String ticketId;
    // @JsonProperty annotation specifies that this field should be serialized/deserialized as "event-name" in JSON
    @JsonProperty("event-name")
    private String eventName;
    // The @JsonProperty annotation specifies that this field should be serialized/deserialized as "ticket-price" in JSON
    @JsonProperty("ticket-price")
    private BigDecimal ticketPrice;

    public Ticket(String eventName, BigDecimal ticketPrice) {
        this.ticketId = UUID.randomUUID().toString(); // Generate random UUID for ticket ID
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }
}
