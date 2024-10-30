package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.SavedBooking;
import mk.finki.ukim.web.lab.repository.EventRepository;
import mk.finki.ukim.web.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<SavedBooking> getSavedBookings() {
        return eventRepository.getSavedBookings();
    }

    @Override
    public void addBooking(String eventName, String attendeeName,int tickets) {
         eventRepository.addBooking(eventName,attendeeName,tickets);
    }
}
