package mk.finki.ukim.web.lab.repository;

import mk.finki.ukim.web.lab.bootstrap.DataHolder;
import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.SavedBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    private final List<SavedBooking> savedBookings = new ArrayList<>();

    public List<Event> findAll() {
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text, double popularity) {
        return DataHolder.events
                .stream()
                .filter(event -> (text == null || text.isEmpty() || event.getName().contains(text) || event.getDescription().contains(text))
                        && event.getPopularityScore() >= popularity)
                .collect(Collectors.toList());
    }

    public List<SavedBooking> getSavedBookings() {
        return savedBookings;
    }

    public void addBooking(String eventName, String attendeeName, int tickets) {
        boolean bookingExists = false;

        for (SavedBooking booking : savedBookings) {
            // Check if booking already exists for this attendee and event
            if (booking.getEventName().equals(eventName) && booking.getAttendeeName().equals(attendeeName)) {
                booking.setNumberOfTickets(booking.getNumberOfTickets() + tickets);
                bookingExists = true;
                break;
            }
        }

        // If no booking found, add a new one
        if (!bookingExists) {
            savedBookings.add(new SavedBooking(eventName, attendeeName, tickets));
        }
    }

}

