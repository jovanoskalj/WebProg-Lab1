package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.Location;
import mk.finki.ukim.web.lab.model.SavedBooking;
import mk.finki.ukim.web.lab.repository.EventRepository;
import mk.finki.ukim.web.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text,double popularity) {
        return eventRepository.searchEvents(text,popularity);
    }

//    @Override
//    public List<SavedBooking> getSavedBookings() {
//        return eventRepository.getSavedBookings();
//    }

    @Override
    public void addBooking(String eventName, String attendeeName, int tickets) {
        try {
            eventRepository.addBooking(eventName, attendeeName, tickets);
        } catch (IllegalArgumentException e) {

            throw new RuntimeException("Booking failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> save(String name, String description, Double popularityScore, Location id) {
        return eventRepository.save(name, description, popularityScore, id);
    }

}
