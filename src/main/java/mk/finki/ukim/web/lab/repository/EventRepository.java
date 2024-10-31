package mk.finki.ukim.web.lab.repository;

import mk.finki.ukim.web.lab.bootstrap.DataHolder;
import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.SavedBooking;
import mk.finki.ukim.web.lab.model.exception.InvalidBookingException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    private final List<SavedBooking> savedBookings = new ArrayList<>();

    public List<Event> findAll() {
        return DataHolder.events;
    }

    // Method to handle ticket booking
    public boolean bookTickets(String eventName, int ticketsToBook) throws IllegalArgumentException {
        Optional<Event> event = DataHolder.events.stream()
                .filter(e -> e.getName().equals(eventName))
                .findFirst();

        if (event.isPresent()) {
            Event currentEvent = event.get();

            if (ticketsToBook > currentEvent.getTicketCount()) {
                throw new InvalidBookingException("Not enough tickets available.");

            }
            // namaluvanje na brojot na dostapni bileti
            currentEvent.setTicketCount(currentEvent.getTicketCount() - ticketsToBook);
            return true;
        } else {
            throw new InvalidBookingException("Event not found.");
        }
    }

    // Existing search and booking methods
    public List<Event> searchEvents(String text, double popularity) {
        return DataHolder.events.stream()
                .filter(event -> (text == null || text.isEmpty() || event.getName().contains(text)
                        || event.getDescription().contains(text)) && event.getPopularityScore() >= popularity)
                .collect(Collectors.toList());
    }

    public void addBooking(String eventName, String attendeeName, int tickets) {
        // Check and book tickets before adding the booking
        bookTickets(eventName, tickets);
        boolean bookingExists = false;

        for (SavedBooking booking : savedBookings) {
            if (booking.getEventName().equals(eventName) && booking.getAttendeeName().equals(attendeeName)) {
                booking.setNumberOfTickets(booking.getNumberOfTickets() + tickets);
                bookingExists = true;
                break;
            }
        }

        if (!bookingExists) {
            savedBookings.add(new SavedBooking(eventName, attendeeName, tickets));
        }
    }
}
