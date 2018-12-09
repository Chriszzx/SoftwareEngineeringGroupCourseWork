package com.trafficmon;

import java.math.BigDecimal;

public interface OperationService {
    public abstract void investigate(Vehicle vehicle);
    public abstract void penaltyNotice(Vehicle vehicle, int charge);
}
