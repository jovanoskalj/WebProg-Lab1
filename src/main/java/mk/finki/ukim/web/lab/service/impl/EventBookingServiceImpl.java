package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.EventBooking;
import mk.finki.ukim.web.lab.model.SavedBooking;
import mk.finki.ukim.web.lab.repository.EventRepository;
import mk.finki.ukim.web.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventRepository eventRepository;

    public EventBookingServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        eventRepository.addBooking(eventName, numberOfTickets); // Store the booking
        return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
    }

    @Override
    public List<SavedBooking> getAllBookings() {
        return eventRepository.getSavedBookings(); // Fetch all bookings
    }
}
