package com.trafficmon;

import com.trafficmon.Vehicle;
import com.trafficmon.ZoneBoundaryCrossing;
import org.junit.Test;
import org.joda.time.Hours;
import org.joda.time.LocalTime;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
    Test functionality of ZoneBoundaryCrossing class.
*/

public class ZoneBoundaryCrossingTest {

    LocalTime local = new LocalTime();
    @Test
    public void getVehicleTest(){
        ZoneBoundaryCrossing crossing = new ZoneBoundaryCrossing(Vehicle.withRegistration("AA")) {};
        assertTrue(crossing.getVehicle().equals(Vehicle.withRegistration("AA")));
    }

    @Test
    public void timestampTest()
    {
        ZoneBoundaryCrossing crossing = new ZoneBoundaryCrossing(Vehicle.withRegistration("AA")) {};
        assertEquals(crossing.timestamp(),local.getHourOfDay()*60+local.getMinuteOfHour());
    }

}