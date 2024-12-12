package lk.limesh.ticketingapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Ticket")
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(
            name = "ticket_id",
            updatable = false,
            nullable = false
    )

    @SequenceGenerator(
            name = "ticket_id",
            sequenceName = "ticket_id"
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_id"
    )
    private Long ticketId;

    @JsonProperty("event-name")
    @Column(
            name = "event_name",
            columnDefinition = "TEXT"
    )
    private String eventName;

    @JsonProperty("ticket-price")
    @Column(
            name = "ticket_price"
    )
    private BigDecimal ticketPrice;

    /**
     * Constructor for creating a Ticket with event name and ticket price.
     * @param eventName The name of the event.
     * @param ticketPrice The price of the ticket.
     **/
    public Ticket(String eventName, BigDecimal ticketPrice) {
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }
}
