package test;
import com.trafficmon.Vehicle;
import com.trafficmon.ZoneBoundaryCrossing;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ZoneBoundaryCrossingTest {

    @Test
    public void getVehicleTest(){
        ZoneBoundaryCrossing crossing = new ZoneBoundaryCrossing(Vehicle.withRegistration("AA")) {};
        assertTrue(crossing.getVehicle().equals(Vehicle.withRegistration("AA")));
    }

    @Test
    public void timestampTest()
    {
        ZoneBoundaryCrossing crossing = new ZoneBoundaryCrossing(Vehicle.withRegistration("AA")) {};
        assertEquals(crossing.timestamp(),System.currentTimeMillis());
    }

}
