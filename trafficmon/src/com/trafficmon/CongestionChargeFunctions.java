package com.trafficmon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CongestionChargeFunctions {

    private static final BigDecimal CHARGE_RATE_POUNDS_PER_MINUTE = new BigDecimal(0.05);

    static final List<ZoneBoundaryCrossing> eventLog = new ArrayList<ZoneBoundaryCrossing>();

    int minutesBetween(long startTimeMs, long endTimeMs) {
        return (int) Math.ceil((endTimeMs - startTimeMs) / (1000.0 * 60.0));
    }

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

    BigDecimal calculateChargeForTimeInZone(List<ZoneBoundaryCrossing> crossings) {

        BigDecimal charge = new BigDecimal(0);

        ZoneBoundaryCrossing lastEvent = crossings.get(0);

        for (ZoneBoundaryCrossing crossing : crossings.subList(1, crossings.size())) {

            if (crossing instanceof ExitEvent) {
                charge = charge.add(
                        new BigDecimal(minutesBetween(lastEvent.timestamp(), crossing.timestamp()))
                                .multiply(CHARGE_RATE_POUNDS_PER_MINUTE));
            }

            lastEvent = crossing;
        }

        return charge;
    }

    BigDecimal newCalculateChargeForTimeInZone(List<ZoneBoundaryCrossing> crossings) {
        BigDecimal charge = new BigDecimal(0);

        int time = 0;
        BigDecimal beforeTwoPM = new BigDecimal(4);
        BigDecimal afterTwoPM = new BigDecimal(6);
        BigDecimal moreThanFourHours = new BigDecimal(12);

        ZoneBoundaryCrossing lastEvent = crossings.get(0);
        ZoneBoundaryCrossing xlastEvent = lastEvent;

        for (ZoneBoundaryCrossing crossing : crossings.subList(1, crossings.size())) {

            if (crossing instanceof ExitEvent) {
                time += newMinutesBetween(lastEvent.timestamp(), crossing.timestamp());
            }
            lastEvent = crossing;
        }
        if (time > 240) {
            charge = charge.add(moreThanFourHours);
        }
        else  {
            if (xlastEvent.timestamp() < 840) {
                charge = charge.add(beforeTwoPM);
            } else {
                charge = charge.add(afterTwoPM);
            }
        }
        return charge;
    }
}

