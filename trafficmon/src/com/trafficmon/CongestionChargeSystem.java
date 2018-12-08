package com.trafficmon;

import java.math.BigDecimal;
import java.util.*;

class CongestionChargeSystem {

    private CongestionChargeFunctions functions = new CongestionChargeFunctions();
    Map<Vehicle, List<ZoneBoundaryCrossing>> crossingsByVehicle = new HashMap<Vehicle, List<ZoneBoundaryCrossing>>();

    void vehicleEnteringZone(Vehicle vehicle) {
        CongestionChargeFunctions.eventLog.add(new EntryEvent(vehicle));
    }

    void vehicleLeavingZone(Vehicle vehicle) {
        if (functions.previouslyRegistered(vehicle)) {
            return;
        }
        CongestionChargeFunctions.eventLog.add(new ExitEvent(vehicle));
    }

    void calculateCharges() {

        for (ZoneBoundaryCrossing crossing : CongestionChargeFunctions.eventLog) {
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

                BigDecimal charge = functions.calculateChargeForTimeInZone(crossings);

                try {
                    RegisteredCustomerAccountsService.getInstance().accountFor(vehicle).deduct(charge);
                } catch (InsufficientCreditException | AccountNotRegisteredException ice) {
                    OperationsTeam.getInstance().issuePenaltyNotice(vehicle, charge);
                }
            }
        }
    }
}

