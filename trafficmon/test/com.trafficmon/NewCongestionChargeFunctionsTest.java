package com.trafficmon;

import org.junit.Test;
import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/*
    Test functionality of new charging system functions.
*/

public class NewCongestionChargeFunctionsTest {

    private NewCongestionChargeSystem newCongestionChargeSystem = new NewCongestionChargeSystem();
    private NewCongestionChargeFunctions functions = new NewCongestionChargeFunctions();
    private FakeTime fakeTime = new FakeTime();
    private Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");
    private Eventlog eventlog = new Eventlog();
    @Test
    public void newMinutesBetweenTest(){
        long startTime = 12;
        long endTime = 18;
        assertThat((int)functions.newMinutesBetween(startTime,endTime),is (6));
    }

    @Test
    public void totalTimeTest(){
        eventlog.getInstance().clear();
        fakeTime.setTime(10, 0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(1);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        long match = 60;
        assertThat(functions.totalTime(eventlog.getInstance()),is(match));
        fakeTime.resetTime();
    }


    @Test
    public void vehicleEnterAndLeaveBeforeTwoPm() {
        eventlog.getInstance().clear();
        fakeTime.setTime(8,0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(2);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat((int)functions.newCalculateChargeForTimeInZone(eventlog.getInstance()), is(6));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleEnterAndLeaveAfterTwoPm() {
        eventlog.getInstance().clear();
        fakeTime.setTime(15, 0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(2);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat((int)functions.newCalculateChargeForTimeInZone(eventlog.getInstance()), is(4));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleStayInsideZoneMoreThanFourHoursCase1() {
        eventlog.getInstance().clear();
        fakeTime.setTime(14, 0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(5);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat((int)functions.newCalculateChargeForTimeInZone(eventlog.getInstance()), is(12));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleStayInsideZoneMoreThanFourHoursCase2(){
        eventlog.getInstance().clear();
        fakeTime.setTime(10, 0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(1);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        fakeTime.delayhours(4);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(1);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat((int)functions.newCalculateChargeForTimeInZone(eventlog.getInstance()),is(6));
        fakeTime.resetTime();
    }
}
