package com.trafficmon;


import java.util.ArrayList;
import java.util.List;

class NewCongestionChargeFunctions {

    private Eventlog eventlog = new Eventlog();

    int newMinutesBetween(long startTime, long endTime) {
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

    int newCalculateChargeForTimeInZone(List<ZoneBoundaryCrossing> crossings) {
        int charge = 0;

        int time = 0;
        int beforeTwoPM = 6;
        int afterTwoPM = 4;
        int moreThanFourHours = 12;

        ZoneBoundaryCrossing lastEvent = crossings.get(0);
        long xlastEvent = lastEvent.timestamp();

        for (ZoneBoundaryCrossing crossing : crossings.subList(1, crossings.size())) {

            if (crossing instanceof ExitEvent) {
                time += newMinutesBetween(lastEvent.timestamp(), crossing.timestamp());
            }
            lastEvent = crossing;
        }
        if (time > 240) {
            charge = charge+moreThanFourHours;
        }
        if (time <= 240 ) {
            if (xlastEvent < 840) {
                charge = charge+beforeTwoPM;
            } else {
                charge = charge+afterTwoPM;
            }
        }
        return charge;
    }
}
