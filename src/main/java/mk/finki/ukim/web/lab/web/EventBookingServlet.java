package mk.finki.ukim.web.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.web.lab.model.EventBooking;
import mk.finki.ukim.web.lab.model.SavedBooking;
import mk.finki.ukim.web.lab.service.EventBookingService;
import mk.finki.ukim.web.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "event-booking", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final EventBookingService bookingService;
    private final EventService eventService;

    public EventBookingServlet(SpringTemplateEngine springTemplateEngine, EventBookingService bookingService, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookingService = bookingService;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String result = req.getParameter("bookingSearch");
//
////        // Get all saved bookings
////        List<SavedBooking> allBookings = eventService.getSavedBookings();
//
////        // Filter bookings based on search input
////        List<SavedBooking> bookingsToSend = allBookings.stream()
////                .filter(booking -> booking.getEventName().toLowerCase().contains(result != null ? result.toLowerCase() : ""))
////                .toList();
//
//        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req, resp);
//        WebContext context = new WebContext(iWebExchange);
//        context.setVariable("savedBookingList", bookingsToSend); // Pass filtered bookings to template
//        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext context = new WebContext(webExchange, req.getLocale());
        String eventName = req.getParameter("eventName");
        String attendeeName = req.getParameter("nameAttendee");
        String attendeeAddress = req.getRemoteAddr();
        int numberOfTickets = Integer.parseInt(req.getParameter("numTickets"));

        EventBooking booking = bookingService.placeBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);
        context.setVariable("booking", booking);

//        // Retrieve only bookings made by this attendee
//        List<SavedBooking> attendeeBookings = bookingService.getBookingsByAttendee(attendeeName);
//        context.setVariable("savedBookingList", attendeeBookings);

        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }

}
