package com.trafficmon;


import java.util.ArrayList;
import java.util.List;


/*
    New functions in new charge systems.
 */

class NewCongestionChargeFunctions {

    private Eventlog eventlog = new Eventlog();

    long newMinutesBetween(long startTime, long endTime) {
        return (int) Math.ceil((endTime - startTime));
    }

    boolean previouslyRegistered(Vehicle vehicle) {
        for (ZoneBoundaryCrossing crossing : eventlog.getInstance()) {
            if (crossing.getVehicle().equals(vehicle)) {
                return false;
            }
        }
        return true;
    }

    boolean checkOrderingOf(List<ZoneBoundaryCrossing> crossings) {

        ZoneBoundaryCrossing lastEvent = crossings.get(0);

        for (ZoneBoundaryCrossing crossing : crossings.subList(1, crossings.size())) {
            if (crossing.timestamp() < lastEvent.timestamp()) {
                return true;
            }
            if (crossing instanceof EntryEvent && lastEvent instanceof EntryEvent) {
                return true;
            }
            if (crossing instanceof ExitEvent && lastEvent instanceof ExitEvent) {
                return true;
            }
            lastEvent = crossing;
        }

        return false;
    }

    long totalTime(List<ZoneBoundaryCrossing> crossings){
        long time = 0;
        ZoneBoundaryCrossing lastEvent = crossings.get(0);
        long timeLastEvent = lastEvent.timestamp();
        for (ZoneBoundaryCrossing crossing : crossings.subList(1, crossings.size())) {

            if (crossing instanceof ExitEvent) {
                time += newMinutesBetween(lastEvent.timestamp(), crossing.timestamp());
            }
            lastEvent = crossing;
        }
        return time;
    }


    long newCalculateChargeForTimeInZone(List<ZoneBoundaryCrossing> crossings) {

        Specification CurrentStandard = new Specification();
        long time = totalTime(crossings);
        ZoneBoundaryCrossing lastEvent = crossings.get(0);
        long first = lastEvent.timestamp();
        return CurrentStandard.Current(first,time);

    }
}
