package mk.finki.ukim.web.lab.service.impl;

import mk.finki.ukim.web.lab.model.Location;
import mk.finki.ukim.web.lab.repository.LocationRepository;
import mk.finki.ukim.web.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
