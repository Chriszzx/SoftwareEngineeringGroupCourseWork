package com.trafficmon;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class NewCongestionChargeSystemTest{

    private Vehicle vehicle1 = Vehicle.withRegistration("AA");
    private Vehicle vehicle2 = Vehicle.withRegistration("BB");
    private Vehicle vehicle3 = Vehicle.withRegistration("CC");
    private NewCongestionChargeFunctions functions = new NewCongestionChargeFunctions();


    @Test
    public void addEventtoMapTest()
    {
        NewCongestionChargeSystem system = new NewCongestionChargeSystem();
        functions.vehicleEnteringZone(vehicle1);
        functions.vehicleEnteringZone(vehicle2);
        functions.vehicleEnteringZone(vehicle3);
        functions.vehicleLeavingZone(vehicle1);
        functions.vehicleLeavingZone(vehicle2);
        functions.vehicleLeavingZone(vehicle3);

        system.addEventtoMap();

        assertTrue(system.crossingsByVehicle.get(vehicle1).get(0) instanceof EntryEvent);
        assertTrue(system.crossingsByVehicle.get(vehicle1).get(1) instanceof ExitEvent);
    }
}