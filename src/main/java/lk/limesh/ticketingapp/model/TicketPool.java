package lk.limesh.ticketingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Queue;

@AllArgsConstructor
@Data
public class TicketPool {
    private int maxTicketCapacity;
    private Queue<Ticket> ticketQueue;
}
