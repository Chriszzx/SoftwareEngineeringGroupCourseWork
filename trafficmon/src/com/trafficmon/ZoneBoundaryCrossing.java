package com.trafficmon;

import org.joda.time.LocalTime;

public class ZoneBoundaryCrossing {

    private final Vehicle vehicle;
    private long timestamp;
    private final long time;

    public ZoneBoundaryCrossing(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.time =  this.timestamp=(new LocalTime().getHourOfDay())*60+new LocalTime().getMinuteOfHour();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public long timestamp() {
        return time;
    }
}
