package com.trafficmon;

public class Example {
    public static void main(String[] args) throws Exception {
        CongestionChargeSystem congestionChargeSystem = new CongestionChargeSystem();
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("A123 XYZ"));
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("B123 XYZ"));
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("C123 XYZ"));
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("E123 XYZ"));
        delaySeconds(1);

        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("A123 XYZ"));
        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("B123 XYZ"));
        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("C123 XYZ"));
        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("D123 XYZ"));
        congestionChargeSystem.calculateCharges();
    }
    private static void delayMinutes(int mins) throws InterruptedException {
        delaySeconds(mins * 60);
    }
    private static void delaySeconds(int secs) throws InterruptedException {
        Thread.sleep(secs * 1000);
    } }
