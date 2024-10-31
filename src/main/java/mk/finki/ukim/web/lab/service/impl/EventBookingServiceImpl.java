package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.EventBooking;
import mk.finki.ukim.web.lab.model.SavedBooking;
import mk.finki.ukim.web.lab.model.exception.InvalidBookingException;
import mk.finki.ukim.web.lab.repository.EventRepository;
import mk.finki.ukim.web.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventRepository eventRepository;

    public EventBookingServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {

            eventRepository.addBooking(eventName,attendeeName, numberOfTickets);
            return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);



    }


}

