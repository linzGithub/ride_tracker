package com.pluralsight.service;

import java.util.List;

import com.pluralsight.model.Ride;

public interface RideService {

    public List<Ride> getRides();
        
    public Ride createRide(Ride ride);

    public Ride modifyRide(Ride ride);

}