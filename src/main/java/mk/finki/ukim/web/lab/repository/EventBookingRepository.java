package mk.finki.ukim.web.lab.repository;

import mk.finki.ukim.web.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventBookingRepository {
    public final List<EventBooking> bookings = new ArrayList<>();

    public void save(EventBooking booking) {
        bookings.add(booking);
    }

    public List<EventBooking> findAll() {
        return new ArrayList<>(bookings);
    }
    // New method to add
    public List<EventBooking> getAllBookings() {
        return new ArrayList<>(bookings);
    }
}
