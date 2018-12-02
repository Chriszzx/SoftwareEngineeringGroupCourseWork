package NewTest;

import com.trafficmon.Vehicle;
import com.trafficmon.ZoneBoundaryCrossing;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
