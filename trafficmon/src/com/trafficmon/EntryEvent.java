package com.trafficmon;

/*
Trigger when vehicle enter zone boundary.
 */

public class EntryEvent extends ZoneBoundaryCrossing {
    public EntryEvent(Vehicle vehicleRegistration) {
        super(vehicleRegistration);
    }
}
