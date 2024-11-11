package mk.finki.ukim.web.lab.web.Controller;

import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.Location;
import mk.finki.ukim.web.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/events")

public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error");
        }
        List<Event> events = this.eventService.listAll();
        model.addAttribute("events", events);
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
            return "add-event";
        }
        return "redirect:/events?error=EventNotFound";


    }
//    @GetMapping("/add-form")
//    public String addEvent(Model model) {
//        List<Event> events = this.eventService.listAll();
//        List<Category> categories = this.categoryService.listCategories();
//        model.addAttribute("manufacturers", manufacturers);
//        model.addAttribute("categories", categories);
//        return "addEvent";
//    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Double popularityScore,
                            @RequestParam Location ID)
                              {
        this.eventService.save(name, description, popularityScore,ID);
        return "redirect:/events";
    }
}
