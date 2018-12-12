package com.trafficmon;

import java.math.BigDecimal;

public class NewStandard extends ChargingStandard {

    BigDecimal CalculateCharge(long first, long time){

        long charge = 0;
        if (time > 240) { charge = 12; }

        if (time <= 240 ) {
            if (first < 840) {
                charge = 6;
            } else {
                charge = 4;
            }
        }
        return new BigDecimal(charge);
    }
}