package com.trafficmon;


public class newExample {
    public static void main(String[] args) throws Exception {
        NewCongestionChargeSystem congestionChargeSystem =new NewCongestionChargeSystem();
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("A123 XYZ"));
        delaySeconds(15);
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("J091 4PY"));
        delayMinutes(30);
        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("A123 XYZ"));
        delayMinutes(10);
        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("J091 4PY"));
        congestionChargeSystem.newCalculateCharges();
    }
    private static void delayMinutes(int mins) throws InterruptedException {
        delaySeconds(mins * 60);
    }
    private static void delaySeconds(int secs) throws InterruptedException {
        Thread.sleep(secs * 1000);
    }
}