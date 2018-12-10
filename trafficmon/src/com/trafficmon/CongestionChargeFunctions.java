package com.trafficmon;

import com.trafficmon.EntryEvent;
import com.trafficmon.ExitEvent;
import com.trafficmon.Vehicle;
import com.trafficmon.ZoneBoundaryCrossing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/*
    This class contains all old private methods in system. We made these methods package private in order to test these methods.
 */

class CongestionChargeFunctions {



    private static final BigDecimal CHARGE_RATE_POUNDS_PER_MINUTE = new BigDecimal(0.05);

    static final List<ZoneBoundaryCrossing> eventLog = new ArrayList<>();

    int minutesBetween(long startTimeMs, long endTimeMs) {
        return (int) Math.ceil((endTimeMs - startTimeMs) / (1000.0 * 60.0));
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
}

