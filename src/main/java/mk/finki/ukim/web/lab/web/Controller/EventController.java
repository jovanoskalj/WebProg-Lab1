package mk.finki.ukim.web.lab.web.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.Location;
import mk.finki.ukim.web.lab.service.EventService;
import mk.finki.ukim.web.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")

public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model, HttpServletRequest req) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error");
        }
        List<Event> events = this.eventService.listAll();
        model.addAttribute("events", events);
        model.addAttribute("clientIpAddress",req.getRemoteAddr());
        return "listEvents.html";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        this.eventService.deleteById(id);
        return "redirect:/events";
    }


    @GetMapping("/edit-form/{id}")
    public String editEvent(@PathVariable Long id, Model model) {

        if (this.eventService.findById(id).isPresent()) {
            Event event = this.eventService.findById(id).get();
            List<Location> locations = this.locationService.findAll();

            model.addAttribute("locations", locations);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";
    }

    @GetMapping("/add-form")
    public String addEvent(Model model) {
        List<Event> events = this.eventService.listAll();
        List<Location> locations = this.locationService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("locations", locations);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Long locationId) {
        Location location = locationService.findById(locationId).orElse(null);
        if (location == null) {
            return "redirect:/events?error=InvalidLocation";
        }
        this.eventService.save(name, description, popularityScore, location);
        return "redirect:/events";
    }

}
