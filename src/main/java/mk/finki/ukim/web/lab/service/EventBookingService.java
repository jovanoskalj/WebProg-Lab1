package mk.finki.ukim.web.lab.service;

import mk.finki.ukim.web.lab.model.EventBooking;
import mk.finki.ukim.web.lab.model.SavedBooking;

import java.util.List;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
    List<SavedBooking> getBookingsByAttendee(String attendeeName); // New method
}

