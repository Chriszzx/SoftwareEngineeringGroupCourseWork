package com.trafficmon;

import NewTest.FakeTime;
import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.joda.time.LocalTime;


public class Example {
    public static void main(String[] args) throws Exception {
        CongestionChargeSystem congestionChargeSystem = new CongestionChargeSystem();
        FakeTime faketime = new FakeTime();
        faketime.setTime(16,1);
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("A123 XYZ"));
        faketime.delayhours(2);
        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("A123 XYZ"));

        congestionChargeSystem.calculateCharges();

    }
}
