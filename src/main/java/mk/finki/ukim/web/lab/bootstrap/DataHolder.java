package mk.finki.ukim.web.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.web.lab.model.Event;
import mk.finki.ukim.web.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();
    public static List<Location> locations = new ArrayList<>();



    @PostConstruct
    public void init() {
        locations.add(new Location("City Art Gallery", "123 Main St", "50", "A large gallery showcasing contemporary and classic art."));
        locations.add(new Location("Sculpture Garden", "456 Park Ave", "150", "An outdoor space filled with beautiful sculptures."));
        locations.add(new Location("Art Studio Downtown", "789 Elm St", "30", "A cozy studio for art workshops and classes."));
        locations.add(new Location("Modern Art Museum", "321 Museum Blvd", "200", "A museum dedicated to modern and abstract art pieces."));
        locations.add(new Location("Street Art District", "101 Urban Rd", "500", "An open area featuring diverse and colorful street art."));

        events.add(new Event("Art Showcase", "An exhibition featuring contemporary art from local artists", 15.00,2,locations.get(3)));
        events.add(new Event("Sculpture Garden Tour", "Explore magnificent sculptures in an outdoor garden", 20.00,10,locations.get(1)));
        events.add(new Event("Portrait Painting Workshop", "A hands-on workshop for beginners in portrait painting", 12.00,7,locations.get(2)));
        events.add(new Event("Art History Talk", "An insightful talk on the evolution of modern art", 8.00,6,locations.get(1)));
        events.add(new Event("Street Art Walk", "Discover and learn about street art in the city", 10.00,12,locations.get(4)));
        events.add(new Event("Digital Art Workshop", "A workshop on creating art with digital tools", 3.00,4,locations.get(1)));
        events.add(new Event("Pottery Class", "Create and take home your own pottery masterpiece", 25.00,28,locations.get(0)));
        events.add(new Event("Live Art Performance", "Watch artists create masterpieces in real-time", 15.00,4,locations.get(2)));
        events.add(new Event("Watercolor Painting Class", "Learn watercolor techniques with a pro artist", 6.00,4,locations.get(3)));
        events.add(new Event("Art Therapy Session", "Experience art as a form of self-expression and relaxation", 10.00,9,locations.get(2)));




    }
}
