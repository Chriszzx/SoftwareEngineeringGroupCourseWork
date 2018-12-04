package com.trafficmon;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.joda.time.LocalTime;


public class Example {
    public static void main(String[] args) throws Exception {
//        CongestionChargeSystem congestionChargeSystem = new CongestionChargeSystem();
//        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("A123 XYZ"));
//        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("B123 XYZ"));
//        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("C123 XYZ"));
//        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("E123 XYZ"));
//        delaySeconds(1);
//
//        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("A123 XYZ"));
//        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("B123 XYZ"));
//        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("C123 XYZ"));
//        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("D123 XYZ"));
//        congestionChargeSystem.calculateCharges();

        int time=new LocalTime().getHourOfDay();
        System.out.print(time);
    }
    private static void delayMinutes(int mins) throws InterruptedException {
        try {
            delaySeconds(mins * 60);
        }
        catch (InterruptedException e)
        {
            System.out.print("Error!");
        }
    }
    private static void delaySeconds(int secs) throws InterruptedException {
        try {
            Thread.sleep(secs * 1000);
        }
        catch (InterruptedException e)
        {
            System.out.print("Error!");
        }
    }
}
