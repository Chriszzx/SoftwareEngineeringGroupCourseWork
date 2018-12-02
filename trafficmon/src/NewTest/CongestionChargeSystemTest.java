package NewTest;

import com.trafficmon.CongestionChargeSystem;
import com.trafficmon.ExitEvent;
import com.trafficmon.EntryEvent;
import com.trafficmon.Vehicle;
import com.trafficmon.ZoneBoundaryCrossing;
import org.jmock.Expectations;
import org.junit.Test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CongestionChargeSystemTest {

    @Test
    public void vehicleenteringzoneTest()
    {
        CongestionChargeSystem system = new CongestionChargeSystem();
        Vehicle vehicle = Vehicle.withRegistration("AA");
        system.vehicleEnteringZone(vehicle);
        assertTrue(system.eventLog.get(0).getVehicle().equals(vehicle));
        assertTrue(system.eventLog.get(0) instanceof EntryEvent);
    }

    @Test
    public void vehicleleavingzoneTest()
    {
        CongestionChargeSystem system = new CongestionChargeSystem();
        Vehicle vehicle = Vehicle.withRegistration("AA");
        system.vehicleEnteringZone(vehicle);
        system.vehicleLeavingZone(vehicle);
        assertTrue(system.eventLog.get(1).getVehicle().equals(vehicle));
        assertTrue(system.eventLog.get(1) instanceof ExitEvent);
    }

    @Test
    public void hoursbetweenTest()
    {
        CongestionChargeSystem system = new CongestionChargeSystem();
        assertEquals(system.minutesBetween(System.currentTimeMillis(),System.currentTimeMillis()),0);
    }

    @Test
    public void previousRegisteredTest()
    {
        CongestionChargeSystem system = new CongestionChargeSystem();
        Vehicle vehicle1 = Vehicle.withRegistration("AA");
        Vehicle vehicle2 = Vehicle.withRegistration("BB");
        system.vehicleEnteringZone(Vehicle.withRegistration("AA"));
        system.vehicleLeavingZone(Vehicle.withRegistration("AA"));
        assertTrue(system.previouslyRegistered(vehicle1));
        assertFalse(system.previouslyRegistered(vehicle2));
    }

}
