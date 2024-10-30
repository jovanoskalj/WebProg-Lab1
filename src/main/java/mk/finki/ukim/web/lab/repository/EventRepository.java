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

    public void addBooking(String eventName, int tickets) {
        boolean bookingMade = false;
        for (SavedBooking booking : savedBookings) {
            if (booking.getEventName().equals(eventName)) {
                booking.setNumberOfTickets(booking.getNumberOfTickets() + tickets);
                bookingMade = true;
                break;
            }
        }
        // If no booking exists, add a new one
        if (!bookingMade) {
            savedBookings.add(new SavedBooking(eventName, tickets));
        }
    }
}

