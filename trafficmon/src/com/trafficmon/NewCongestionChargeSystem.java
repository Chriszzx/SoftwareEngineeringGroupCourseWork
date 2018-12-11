package com.trafficmon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    New system.
 */

public class NewCongestionChargeSystem {

    private Eventlog eventlog = new Eventlog();
    OperationService operationteam = new OperationAdaptor();
    CustomerAccountsService customerservice = new CustomerAccountsAdaptor();
    private NewCongestionChargeFunctions functions = new NewCongestionChargeFunctions();
    Map<Vehicle, List<ZoneBoundaryCrossing>> crossingsByVehicle = new HashMap<Vehicle, List<ZoneBoundaryCrossing>>();

    public void vehicleEnteringZone(Vehicle vehicle) {
        eventlog.getInstance().add(new EntryEvent(vehicle));
    }

    public void vehicleLeavingZone(Vehicle vehicle) {
        if (functions.previouslyRegistered(vehicle)) {
            return;
        }
        eventlog.getInstance().add(new ExitEvent(vehicle));
    }

    public void addEventtoMap()
    {
        for (ZoneBoundaryCrossing crossing : eventlog.getInstance()) {
            if (!crossingsByVehicle.containsKey(crossing.getVehicle())) {
                crossingsByVehicle.put(crossing.getVehicle(), new ArrayList<ZoneBoundaryCrossing>());
            }
            crossingsByVehicle.get(crossing.getVehicle()).add(crossing);
        }
    }

    public void newCalculateCharges() {
        addEventtoMap();
        for (Map.Entry<Vehicle, List<ZoneBoundaryCrossing>> vehicleCrossings : crossingsByVehicle.entrySet()) {
            Vehicle vehicle = vehicleCrossings.getKey();
            List<ZoneBoundaryCrossing> crossings = vehicleCrossings.getValue();

            if (functions.checkOrderingOf(crossings)) {
                operationteam.investigate(vehicle);
            } else {
                long charge = functions.newCalculateChargeForTimeInZone(crossings);
                try {
                    customerservice.deductCharge(vehicle, charge);
                }
                catch (InsufficientCreditException | AccountNotRegisteredException ice)
                {
                    operationteam.penaltyNotice(vehicle,charge);
                }
            }
        }
    }
}
