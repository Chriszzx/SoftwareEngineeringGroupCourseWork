package com.trafficmon;

import java.math.BigDecimal;

/*
    This is adaptor interface for handling illegal vehicles issues which supplied by third-party library.
 */

public interface OperationService {
    public abstract void investigate(Vehicle vehicle);
    public abstract void penaltyNotice(Vehicle vehicle, long charge);
}
