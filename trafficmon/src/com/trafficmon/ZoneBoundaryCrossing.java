package com.trafficmon;

/*
    Boundary-crossing event class.
 */

public class ZoneBoundaryCrossing {

    private final Vehicle vehicle;
    private long timestamp;

    public ZoneBoundaryCrossing(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.timestamp=new FakeTime().getTime();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public long timestamp() {
        return timestamp;
    }
}
