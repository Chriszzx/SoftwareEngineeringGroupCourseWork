package com.trafficmon;


public class Example {
    public static void main(String[] args) throws Exception {
        CongestionChargeSystem congestionChargeSystem = new CongestionChargeSystem();
        FakeTime faketime = new FakeTime();
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("A123 XYZ"));
        faketime.delayhours(5);
        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("A123 XYZ"));

        congestionChargeSystem.calculateCharges();

    }
}
