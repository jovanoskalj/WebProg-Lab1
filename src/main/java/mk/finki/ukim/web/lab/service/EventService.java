package mk.finki.ukim.web.lab.service;

import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.SavedBooking;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text,double popularity);
//    List<SavedBooking> getSavedBookings();
    void addBooking(String eventName,String attendeeName,int tickets);
}
