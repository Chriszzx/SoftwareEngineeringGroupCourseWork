package com.trafficmon;

/*
    Trigger when vehicle enter zone boundary.
 */


public class ExitEvent extends ZoneBoundaryCrossing {
    public ExitEvent(Vehicle vehicle) {
        super(vehicle);
    }
}
