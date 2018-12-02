package test;
import com.trafficmon.Vehicle;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class VehicleTest{

    @Test
    public void toStringTest()
    {
        Vehicle vehicle=Vehicle.withRegistration("AA");
        assertThat(vehicle.toString(),is("Vehicle [" + "AA" + "]"));
    }

    @Test
    public void equalstoTest()
    {
        Vehicle vehicle=Vehicle.withRegistration("AA");
        Vehicle vehicle2=Vehicle.withRegistration("AA");
        assertTrue(vehicle.equals(vehicle2));
        assertFalse(vehicle.equals("AA"));
    }

    @Test
    public void hashCodeTest()
    {
        Vehicle vehicle=Vehicle.withRegistration("AA");
        Vehicle vehicle1=Vehicle.withRegistration("AA");
        assertEquals(vehicle.hashCode(),vehicle1.hashCode());
        assertEquals(Vehicle.withRegistration(null).hashCode(),0);

    }
}
