package com.trafficmon;

import com.trafficmon.CongestionChargeFunctions;
import com.trafficmon.CongestionChargeSystem;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CongestionChargeFunctionsTest {

    @Test
    public void minutesBetweenTest(){
        CongestionChargeFunctions functions = new CongestionChargeFunctions();
        long startTimeMs = 120000;
        long endTimeMs = 180000;
        assertThat(functions.minutesBetween(startTimeMs,endTimeMs),is(1));
    }

    @Test
    public void previouslyRegisteredTest(){
        CongestionChargeSystem congestionChargeSystem = new CongestionChargeSystem();
        Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");
        Vehicle vehicle1 = Vehicle.withRegistration("XXX XXX");
        ZoneBoundaryCrossing zoneBoundaryCrossing = new ZoneBoundaryCrossing(vehicle1);
        congestionChargeSystem.vehicleEnteringZone(vehicle);
        assertEquals(zoneBoundaryCrossing.getVehicle(), vehicle1);
        assertNotEquals(zoneBoundaryCrossing.getVehicle(), vehicle);
    }

}
