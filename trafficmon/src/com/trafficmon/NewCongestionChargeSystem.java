package com.trafficmon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewCongestionChargeSystem {

    CongestionChargeFunctions functions = new CongestionChargeFunctions();
    Map<Vehicle, List<ZoneBoundaryCrossing>> crossingsByVehicle = new HashMap<Vehicle, List<ZoneBoundaryCrossing>>();

    public void vehicleEnteringZone(Vehicle vehicle) {
        functions.eventLog.add(new EntryEvent(vehicle));
    }

    public void vehicleLeavingZone(Vehicle vehicle) {
        if (functions.previouslyRegistered(vehicle)) {
            return;
        }
        functions.eventLog.add(new ExitEvent(vehicle));
    }

    public void newCalculateCharges() {

        for (ZoneBoundaryCrossing crossing : functions.eventLog) {
            if (!crossingsByVehicle.containsKey(crossing.getVehicle())) {
                crossingsByVehicle.put(crossing.getVehicle(), new ArrayList<ZoneBoundaryCrossing>());
            }
            crossingsByVehicle.get(crossing.getVehicle()).add(crossing);
        }

        for (Map.Entry<Vehicle, List<ZoneBoundaryCrossing>> vehicleCrossings : crossingsByVehicle.entrySet()) {
            Vehicle vehicle = vehicleCrossings.getKey();
            List<ZoneBoundaryCrossing> crossings = vehicleCrossings.getValue();

            if (functions.checkOrderingOf(crossings)) {
                OperationsTeam.getInstance().triggerInvestigationInto(vehicle);
            } else {

                BigDecimal charge = functions.newCalculateChargeForTimeInZone(crossings);

                try {
                    RegisteredCustomerAccountsService.getInstance().accountFor(vehicle).deduct(charge);
                } catch (InsufficientCreditException | AccountNotRegisteredException ice) {
                    OperationsTeam.getInstance().issuePenaltyNotice(vehicle, charge);
                }
            }
        }
    }
}
