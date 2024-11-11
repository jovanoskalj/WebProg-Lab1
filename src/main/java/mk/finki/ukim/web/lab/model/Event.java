package mk.finki.ukim.web.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Event {
    private  String name;
    private String description;
    private double popularityScore;
    private int ticketCount;
    private Long id;
    private Location location;

    public Event(String name, String description, double popularityScore, int ticketCount) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.ticketCount = ticketCount;
        Random random = new Random();
        this.id = random.nextLong();
    }
}
