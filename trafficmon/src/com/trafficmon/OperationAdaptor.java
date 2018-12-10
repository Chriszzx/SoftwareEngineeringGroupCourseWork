package com.trafficmon;

import java.math.BigDecimal;

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
