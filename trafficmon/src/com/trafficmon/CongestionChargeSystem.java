package com.trafficmon;

import com.trafficmon.CongestionChargeFunctions;
import com.trafficmon.EntryEvent;
import com.trafficmon.ExitEvent;
import com.trafficmon.Vehicle;
import com.trafficmon.ZoneBoundaryCrossing;

import java.math.BigDecimal;
import java.util.*;


/*
    This is the old system, we edit some features to test functionality.
 */


class CongestionChargeSystem {

    private CongestionChargeFunctions functions = new CongestionChargeFunctions();
    Map<com.trafficmon.Vehicle, List<com.trafficmon.ZoneBoundaryCrossing>> crossingsByVehicle = new HashMap<com.trafficmon.Vehicle, List<com.trafficmon.ZoneBoundaryCrossing>>();

    void vehicleEnteringZone(com.trafficmon.Vehicle vehicle) {
        CongestionChargeFunctions.eventLog.add(new EntryEvent(vehicle));
    }

    void vehicleLeavingZone(com.trafficmon.Vehicle vehicle) {
        if (functions.previouslyRegistered(vehicle)) {
            return;
        }
        CongestionChargeFunctions.eventLog.add(new ExitEvent(vehicle));
    }

    void calculateCharges() {

        for (com.trafficmon.ZoneBoundaryCrossing crossing : CongestionChargeFunctions.eventLog) {
            if (!crossingsByVehicle.containsKey(crossing.getVehicle())) {
                crossingsByVehicle.put(crossing.getVehicle(), new ArrayList<com.trafficmon.ZoneBoundaryCrossing>());
            }
            crossingsByVehicle.get(crossing.getVehicle()).add(crossing);
        }

        for (Map.Entry<com.trafficmon.Vehicle, List<com.trafficmon.ZoneBoundaryCrossing>> vehicleCrossings : crossingsByVehicle.entrySet()) {
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

