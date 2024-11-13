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
                                 @RequestParam String Name,
                                 @RequestParam String attendeeAddress,
                                 @RequestParam String numTickets,
                                 @RequestParam(required = false) String error,
                                 Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
            return "listEvents";
        }
        int numberOfTickets=Integer.parseInt(numTickets);
        EventBooking eventBooking=eventBookingService.placeBooking(eventName,Name,attendeeAddress,numberOfTickets);
        eventBookingRepository.bookings.add(eventBooking);
        List<EventBooking> allbookings =eventBookingService.filterBookings(Name);

        model.addAttribute("booking",eventBooking);
        model.addAttribute("allbookings",allbookings);

        return "bookingConfirmation.html";
    }
}

