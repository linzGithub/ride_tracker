package com.pluralsight.service;

import java.util.List;

import com.pluralsight.model.Ride;

public interface RideService {

    List<Ride> getRides();
        
    Ride createRide(Ride ride);

    Ride modifyRide(Ride ride);

    Ride getRide(Integer id);
}