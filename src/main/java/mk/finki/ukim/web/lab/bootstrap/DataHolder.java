package mk.finki.ukim.web.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.web.lab.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();



    @PostConstruct
    public void init() {
        events.add(new Event("Art Showcase", "An exhibition featuring contemporary art from local artists", 15.00));
        events.add(new Event("Sculpture Garden Tour", "Explore magnificent sculptures in an outdoor garden", 20.00));
        events.add(new Event("Portrait Painting Workshop", "A hands-on workshop for beginners in portrait painting", 12.00));
        events.add(new Event("Art History Talk", "An insightful talk on the evolution of modern art", 8.00));
        events.add(new Event("Street Art Walk", "Discover and learn about street art in the city", 10.00));
        events.add(new Event("Digital Art Workshop", "A workshop on creating art with digital tools", 3.00));
        events.add(new Event("Pottery Class", "Create and take home your own pottery masterpiece", 25.00));
        events.add(new Event("Live Art Performance", "Watch artists create masterpieces in real-time", 15.00));
        events.add(new Event("Watercolor Painting Class", "Learn watercolor techniques with a pro artist", 6.00));
        events.add(new Event("Art Therapy Session", "Experience art as a form of self-expression and relaxation", 10.00));


    }
}
