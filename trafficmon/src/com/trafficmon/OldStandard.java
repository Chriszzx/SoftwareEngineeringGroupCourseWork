package com.trafficmon;
import java.math.BigDecimal;

public class OldStandard extends ChargingStandard {
    public static final BigDecimal CHARGE_RATE_POUNDS_PER_MINUTE = new BigDecimal(0.05);

    BigDecimal CalculateCharge(long first, long time){
        return CHARGE_RATE_POUNDS_PER_MINUTE.multiply(new BigDecimal(time));
    }
}