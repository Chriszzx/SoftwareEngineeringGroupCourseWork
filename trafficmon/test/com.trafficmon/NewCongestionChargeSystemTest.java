package com.trafficmon;


import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class NewCongestionChargeSystemTest {

    @Test
    public void vehicleEnterAndLeaveBeforeTwoPm() {
        NewCongestionChargeSystem newCongestionChargeSystem = new NewCongestionChargeSystem();
        CongestionChargeFunctions functions = new CongestionChargeFunctions();
        FakeTime fakeTime = new FakeTime();
        Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");
        fakeTime.setTime(8,0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(2);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(functions.eventLog), is(new BigDecimal(4)));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleEnterAndLeaveAfterTwoPm() {
        NewCongestionChargeSystem newCongestionChargeSystem = new NewCongestionChargeSystem();
        CongestionChargeFunctions functions = new CongestionChargeFunctions();
        FakeTime fakeTime = new FakeTime();
        Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");
        fakeTime.setTime(15,0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(2);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(functions.eventLog), is(new BigDecimal(6)));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleStayInsideZoneMoreThanFourHoursCase1() {
        NewCongestionChargeSystem newCongestionChargeSystem = new NewCongestionChargeSystem();
        CongestionChargeFunctions functions = new CongestionChargeFunctions();
        FakeTime fakeTime = new FakeTime();
        Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");
        fakeTime.setTime(14, 0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.delayhours(5);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(functions.eventLog), is(new BigDecimal(12)));
        fakeTime.resetTime();
    }

    @Test
    public void vehicleStayInsideZoneMoreThanFourHoursCase2(){
        NewCongestionChargeSystem newCongestionChargeSystem = new NewCongestionChargeSystem();
        CongestionChargeFunctions functions = new CongestionChargeFunctions();
        FakeTime fakeTime = new FakeTime();
        Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");
        fakeTime.setTime(10, 0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.setTime(11,0);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        fakeTime.setTime(15,0);
        newCongestionChargeSystem.vehicleEnteringZone(vehicle);
        fakeTime.setTime(16,0);
        newCongestionChargeSystem.vehicleLeavingZone(vehicle);
        assertThat(functions.newCalculateChargeForTimeInZone(functions.eventLog),is(new BigDecimal(4)));
    }
}
