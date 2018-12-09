package com.trafficmon;


public class example {
    public static void main(String[] args) throws Exception {
        NewCongestionChargeSystem congestionChargeSystem =new NewCongestionChargeSystem();
        CalculateCharges calculateCharges = new CalculateCharges();
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("A123 XYZ"));
        delaySeconds(15);
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("J091 4PY"));
        delayMinutes(30);
        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("A123 XYZ"));
        delayMinutes(10);
        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("J091 4PY"));
        calculateCharges.newCalculateCharges();
    }
    private static void delayMinutes(int mins) throws InterruptedException {
        delaySeconds(mins * 60);
    }
    private static void delaySeconds(int secs) throws InterruptedException {
        Thread.sleep(secs * 1000);
    }
}