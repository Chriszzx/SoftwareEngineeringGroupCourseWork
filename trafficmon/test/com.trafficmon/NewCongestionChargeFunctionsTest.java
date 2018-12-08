package com.trafficmon;


import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class NewCongestionChargeFunctionsTest {

    private NewCongestionChargeSystem newCongestionChargeSystem = new NewCongestionChargeSystem();
    private NewCongestionChargeFunctions functions = new NewCongestionChargeFunctions();
    private FakeTime fakeTime = new FakeTime();
    private Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");

    @Test
    public void newMinutesBetweenTest(){
        long startTime = 12;
        long endTime = 18;
        assertThat(functions.newMinutesBetween(startTime,endTime),is (6));
    }


    @Test
    public void vehicleEnterAndLeaveBeforeTwoPm() {
        functions.eventLog.clear();
        fakeTime.setTime(8,0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(2);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(functions.eventLog), is(new BigDecimal(6)));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleEnterAndLeaveAfterTwoPm() {
        functions.eventLog.clear();
        fakeTime.setTime(15, 0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(2);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(functions.eventLog), is(new BigDecimal(4)));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleStayInsideZoneMoreThanFourHoursCase1() {
        functions.eventLog.clear();
        fakeTime.setTime(14, 0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(5);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(functions.eventLog), is(new BigDecimal(12)));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleStayInsideZoneMoreThanFourHoursCase2(){
        functions.eventLog.clear();
        fakeTime.setTime(10, 0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(1);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        fakeTime.delayhours(4);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(1);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(functions.eventLog),is(new BigDecimal(6)));
        fakeTime.resetTime();
    }
}
