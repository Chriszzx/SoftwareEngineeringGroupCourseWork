package com.trafficmon;

import java.math.BigDecimal;

/*
    This is adaptor class for handling illegal vehicles issues which supplied by third-party library.
 */

public class OperationAdaptor implements OperationService {

    @Override
    public void investigate(Vehicle vehicle)
    {
        OperationsTeam.getInstance().triggerInvestigationInto(vehicle);
    }

    @Override
    public void penaltyNotice(Vehicle vehicle, long charge)
    {
        BigDecimal total = new BigDecimal(charge);
        OperationsTeam.getInstance().issuePenaltyNotice(vehicle,total);
    }
}
