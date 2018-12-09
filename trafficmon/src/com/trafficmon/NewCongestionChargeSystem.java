package com.trafficmon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NewCongestionChargeSystem {

    private NewCongestionChargeFunctions functions = new NewCongestionChargeFunctions();

    void vehicleEnteringZone(Vehicle vehicle) {
        NewCongestionChargeFunctions.eventLog.add(new EntryEvent(vehicle));
    }

    void vehicleLeavingZone(Vehicle vehicle) {
        if (functions.previouslyRegistered(vehicle)) {
            return;
        }
        NewCongestionChargeFunctions.eventLog.add(new ExitEvent(vehicle));
    }
}
