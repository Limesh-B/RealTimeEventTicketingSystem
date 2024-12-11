/**
 * Represents the configuration settings for the ticketing system.
 * This interface defines the structure of the configuration object.
 */
export interface Configuration {
  maxTicketCapacity: number;
  totalTickets: number;
  ticketReleaseRate: number;
  customerRetrievalRate: number;
}
