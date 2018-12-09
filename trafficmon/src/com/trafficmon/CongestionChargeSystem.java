package com.trafficmon;

import java.math.BigDecimal;
import java.util.*;

class CongestionChargeSystem {

    private CongestionChargeFunctions functions = new CongestionChargeFunctions();

    void vehicleEnteringZone(Vehicle vehicle) {
        CongestionChargeFunctions.eventLog.add(new EntryEvent(vehicle));
    }

    void vehicleLeavingZone(Vehicle vehicle) {
        if (functions.previouslyRegistered(vehicle)) {
            return;
        }
        CongestionChargeFunctions.eventLog.add(new ExitEvent(vehicle));
    }

    public void calculateCharge(){
        CalculateCharges calculateCharges = new CalculateCharges();
        calculateCharges.calculateCharges();
    }

}
