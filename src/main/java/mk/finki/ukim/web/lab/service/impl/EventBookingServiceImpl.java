package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.EventBooking;
import mk.finki.ukim.web.lab.repository.EventBookingRepository;
import mk.finki.ukim.web.lab.repository.EventRepository;
import mk.finki.ukim.web.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventRepository eventRepository;
    private final EventBookingRepository bookingRepository;

    public EventBookingServiceImpl(EventRepository eventRepository, EventBookingRepository bookingRepository) {
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {

            eventRepository.addBooking(eventName,attendeeName, numberOfTickets);
            return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);



    }

    @Override
    public List<EventBooking> filterBookings(String name) {
        return bookingRepository.findAll().stream()
                .filter(booking -> booking.getAttendeeName().equals(name))
                .toList();
    }


}

