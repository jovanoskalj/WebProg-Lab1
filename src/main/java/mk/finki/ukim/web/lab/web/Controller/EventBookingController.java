package mk.finki.ukim.web.lab.web.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.EventBooking;
import mk.finki.ukim.web.lab.repository.EventBookingRepository;
import mk.finki.ukim.web.lab.service.EventBookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final EventBookingRepository eventBookingRepository;

    public EventBookingController(EventBookingService eventBookingService, EventBookingRepository eventBookingRepository) {
        this.eventBookingService = eventBookingService;
        this.eventBookingRepository = eventBookingRepository;
    }


    @PostMapping
    public String getBookingPage(@RequestParam String eventName,
                                 @RequestParam String attendeeName,
                                 @RequestParam String attendeeAddress,
                                 @RequestParam int numTickets,
                                 @RequestParam(required = false) String error,
                                 Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
            return "listEvents"; // Ensure this redirects to the correct view
        }

        // Debugging: Check if values are coming through properly
        System.out.println("Booking Details:");
        System.out.println("Event Name: " + eventName);
        System.out.println("Attendee Name: " + attendeeName);
        System.out.println("Attendee Address: " + attendeeAddress);
        System.out.println("Number of Tickets: " + numTickets);

        // Create booking and add to repository
        EventBooking eventBooking = eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, numTickets);
        eventBookingRepository.bookings.add(eventBooking);

        // Fetch filtered bookings for attendee
        List<EventBooking> allBookings = eventBookingService.filterBookings(attendeeName);
        model.addAttribute("booking", eventBooking);
        model.addAttribute("allBookings", allBookings);

        return "bookingConfirmation"; // Make sure this view exists and is correctly rendered
    }

}
