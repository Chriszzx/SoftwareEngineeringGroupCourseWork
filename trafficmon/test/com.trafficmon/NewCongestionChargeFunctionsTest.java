package com.trafficmon;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/*
    Test functionality of new charging system functions.
*/

public class NewCongestionChargeFunctionsTest {

    //private NewCongestionChargeSystem newCongestionChargeSystem = new NewCongestionChargeSystem();
    private NewCongestionChargeFunctions functions = new NewCongestionChargeFunctions();
    private FakeTime fakeTime = new FakeTime();
    private Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");
    private Eventlog eventlog = new Eventlog();

    @Test
    public void newMinutesBetweenTest(){
        long startTime = 12;
        long endTime = 18;
        assertThat(functions.newMinutesBetween(startTime,endTime),is (6L));
    }

    @Test
    public void totalTimeTest(){
        eventlog.getInstance().clear();
        fakeTime.setTime(10, 0);
        functions.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(1);
        functions.vehicleLeavingZone(vehicle);
        long match = 60;
        assertThat(functions.totalTime(eventlog.getInstance()),is(match));
        fakeTime.resetTime();
    }


    @Test
    public void vehicleEnterAndLeaveBeforeTwoPm() {
        eventlog.getInstance().clear();
        fakeTime.setTime(8,0);
        functions.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(2);
        functions.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(eventlog.getInstance()), is(new BigDecimal(6)));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleEnterAndLeaveAfterTwoPm() {
        eventlog.getInstance().clear();
        fakeTime.setTime(15, 0);
        functions.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(2);
        functions.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(eventlog.getInstance()), is(new BigDecimal(4)));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleStayInsideZoneMoreThanFourHoursCase1() {
        eventlog.getInstance().clear();
        fakeTime.setTime(14, 0);
        functions.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(5);
        functions.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(eventlog.getInstance()), is(new BigDecimal(12)));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleStayInsideZoneMoreThanFourHoursCase2(){
        eventlog.getInstance().clear();
        fakeTime.setTime(10, 0);
        functions.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(1);
        functions.vehicleLeavingZone(vehicle);
        fakeTime.delayhours(4);
        functions.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(1);
        functions.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(eventlog.getInstance()),is(new BigDecimal(6)));
        fakeTime.resetTime();
    }

    @Test
    public void MapTest()
    {
        Vehicle vehicle1 = Vehicle.withRegistration("AA");
        Vehicle vehicle2 = Vehicle.withRegistration("BB");
        Vehicle vehicle3 = Vehicle.withRegistration("CC");

        NewCongestionChargeSystem system = new NewCongestionChargeSystem();
        functions.vehicleEnteringZone(vehicle1);
        functions.vehicleEnteringZone(vehicle2);
        functions.vehicleEnteringZone(vehicle3);
        functions.vehicleLeavingZone(vehicle1);
        functions.vehicleLeavingZone(vehicle2);
        functions.vehicleLeavingZone(vehicle3);

        system.newCalculateCharges();

        assertTrue(system.crossingsByVehicle.get(vehicle1).get(0) instanceof EntryEvent);
        assertTrue(system.crossingsByVehicle.get(vehicle1).get(1) instanceof ExitEvent);
    }

    @Test
    public void vehicleEnteringAndLeavingZone() {
        NewCongestionChargeFunctions functions = new NewCongestionChargeFunctions();
        Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");
        functions.vehicleEnteringZone(vehicle);
        assertThat(eventlog.getInstance().get(0) instanceof EntryEvent,is(true));
    }

    @Test
    public void CheckOrderingTest(){
        eventlog.getInstance().clear();
        NewCongestionChargeSystem system = new NewCongestionChargeSystem();

        Vehicle vehicle1 = Vehicle.withRegistration("AA");
        Vehicle vehicle2 = Vehicle.withRegistration("BB");
        Vehicle vehicle3 = Vehicle.withRegistration("CC");

        functions.vehicleEnteringZone(vehicle1);
        functions.vehicleEnteringZone(vehicle1);
        functions.vehicleEnteringZone(vehicle2);
        functions.vehicleLeavingZone(vehicle2);
        functions.vehicleLeavingZone(vehicle2);

        fakeTime.setTime(10,0);
        functions.vehicleEnteringZone(vehicle3);
        fakeTime.delayhours(-1);
        functions.vehicleLeavingZone(vehicle3);
        fakeTime.resetTime();

        system.newCalculateCharges();
        assertThat(functions.checkOrderingOf(eventlog.getInstance()), is(true));
    }

    @Test
    public void previousRegisteredTest()
    {
        eventlog.getInstance().clear();
        fakeTime.setTime(10, 0);
        assertThat(functions.previouslyRegistered(vehicle),is(false));
        functions.vehicleEnteringZone(vehicle);
        assertThat(functions.previouslyRegistered(vehicle),is(true));
        fakeTime.resetTime();
    }
}
