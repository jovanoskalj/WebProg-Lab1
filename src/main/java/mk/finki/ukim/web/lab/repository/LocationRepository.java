package mk.finki.ukim.web.lab.repository;

import mk.finki.ukim.web.lab.bootstrap.DataHolder;
import mk.finki.ukim.web.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class LocationRepository {
    public List<Location> findAll(){
        return DataHolder.locations;
    }
}
