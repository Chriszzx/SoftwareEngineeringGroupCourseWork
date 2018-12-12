
package com.trafficmon;

import java.math.BigDecimal;
import java.util.List;

public class ChargingStandard {

    long totalTime(List<ZoneBoundaryCrossing> crossings){
        long time = 0;
        NewCongestionChargeFunctions functions = new NewCongestionChargeFunctions();
        ZoneBoundaryCrossing lastEvent = crossings.get(0);
        for (ZoneBoundaryCrossing crossing : crossings.subList(1, crossings.size())) {

            if (crossing instanceof ExitEvent) {
                time += functions.newMinutesBetween(lastEvent.timestamp(), crossing.timestamp());
            }
            lastEvent = crossing;
        }
        return time;
    }

    BigDecimal CalculateCharge(long first, long time){
        return new BigDecimal(0);
    }

}