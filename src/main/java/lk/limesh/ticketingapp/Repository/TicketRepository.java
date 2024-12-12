package lk.limesh.ticketingapp.Repository;

import lk.limesh.ticketingapp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository <Ticket, Long> {
}
