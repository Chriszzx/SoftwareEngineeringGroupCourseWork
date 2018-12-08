package com.trafficmon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NewCongestionChargeFunctions {

    static final List<ZoneBoundaryCrossing> eventLog = new ArrayList<ZoneBoundaryCrossing>();

    int newMinutesBetween(long startTime, long endTime) {
        return (int) Math.ceil((endTime - startTime));
    }
    boolean previouslyRegistered(Vehicle vehicle) {
        for (ZoneBoundaryCrossing crossing : eventLog) {
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

    BigDecimal newCalculateChargeForTimeInZone(List<ZoneBoundaryCrossing> crossings) {
        BigDecimal charge = new BigDecimal(0);

        int time = 0;
        BigDecimal beforeTwoPM = new BigDecimal(6);
        BigDecimal afterTwoPM = new BigDecimal(4);
        BigDecimal moreThanFourHours = new BigDecimal(12);

        ZoneBoundaryCrossing lastEvent = crossings.get(0);
        long xlastEvent = lastEvent.timestamp();

        for (ZoneBoundaryCrossing crossing : crossings.subList(1, crossings.size())) {

            if (crossing instanceof ExitEvent) {
                time += newMinutesBetween(lastEvent.timestamp(), crossing.timestamp());
            }
            lastEvent = crossing;
        }
        if (time > 240) {
            charge = charge.add(moreThanFourHours);
        }
        if (time <= 240 ) {
            if (xlastEvent < 840) {
                charge = charge.add(beforeTwoPM);
            } else {
                charge = charge.add(afterTwoPM);
            }
        }
        return charge;
    }
}
