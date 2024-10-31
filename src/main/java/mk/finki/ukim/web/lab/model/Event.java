package mk.finki.ukim.web.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Event {
    private  String name;
    private String description;
    private double popularityScore;
    private int ticketCount;


}
