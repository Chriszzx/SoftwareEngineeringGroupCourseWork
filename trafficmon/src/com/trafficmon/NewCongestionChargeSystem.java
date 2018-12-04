package com.trafficmon;

public class NewCongestionChargeSystem {

    private CongestionChargeFunctions functions = new CongestionChargeFunctions();

    public void vehicleEnteringZone(Vehicle vehicle) {
        CongestionChargeFunctions.eventLog.add(new EntryEvent(vehicle));
    }

    public void vehicleLeavingZone(Vehicle vehicle) {
        if (functions.previouslyRegistered(vehicle)) {
            return;
        }
        CongestionChargeFunctions.eventLog.add(new ExitEvent(vehicle));
    }

    public void newCalculateCharges(){

    }


}
