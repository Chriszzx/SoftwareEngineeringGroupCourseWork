package com.trafficmon;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class CongestionChargeSystemTest {

    @Test
    public void vehicleEnteringAndLeavingZone() {
        CongestionChargeSystem congestionChargeSystem = new CongestionChargeSystem();
        CongestionChargeFunctions functions = new CongestionChargeFunctions();
        Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");
        ZoneBoundaryCrossing zoneBoundaryCrossing = new ZoneBoundaryCrossing(vehicle) {
            @Override
            public Vehicle getVehicle() {
                return super.getVehicle();
            }
        };
        congestionChargeSystem.vehicleEnteringZone(vehicle);
        assertThat(functions.eventLog.get(0) instanceof EntryEvent,is(true));
    }

    @Test
    public void MapTest()
    {
        Vehicle vehicle1 = Vehicle.withRegistration("AA");
        Vehicle vehicle2 = Vehicle.withRegistration("BB");
        Vehicle vehicle3 = Vehicle.withRegistration("CC");

        CongestionChargeSystem system = new CongestionChargeSystem();
        system.vehicleEnteringZone(vehicle1);
        system.vehicleEnteringZone(vehicle2);
        system.vehicleEnteringZone(vehicle3);
        system.vehicleLeavingZone(vehicle1);
        system.vehicleLeavingZone(vehicle2);
        system.vehicleLeavingZone(vehicle3);

        system.calculateCharges();

        assertTrue(system.crossingsByVehicle.get(vehicle1).get(0) instanceof EntryEvent);
        assertTrue(system.crossingsByVehicle.get(vehicle1).get(1) instanceof ExitEvent);
    }

    private static void delayMinutes(int mins) throws InterruptedException {
        try {
            delaySeconds(mins * 60);
        }
        catch (InterruptedException e)
        {
            System.out.print("Error!");
        }
    }
    private static void delaySeconds(int secs) throws InterruptedException {
        try {
            Thread.sleep(secs*1000);
        }
        catch (InterruptedException e)
        {
            System.out.print("Error!");
        }
    }
}
