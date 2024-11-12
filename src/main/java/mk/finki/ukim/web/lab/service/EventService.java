package mk.finki.ukim.web.lab.service;

import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.EventBooking;
import mk.finki.ukim.web.lab.model.Location;
import mk.finki.ukim.web.lab.model.SavedBooking;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text,double popularity);
//    List<SavedBooking> getSavedBookings();
    void addBooking(String eventName,String attendeeName,int tickets);

    void deleteById(Long id);
    public Optional<Event> findById(Long id);

    Optional<Event> save(String name, String description, Double popularityScore, Location id);

}
