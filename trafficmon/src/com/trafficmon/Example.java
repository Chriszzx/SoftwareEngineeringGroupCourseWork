package com.trafficmon;


public class Example {
    public static void main(String[] args) throws Exception {
        CongestionChargeSystem congestionChargeSystem = new CongestionChargeSystem();
        FakeTime faketime = new FakeTime();
        faketime.setTime(11,0);
        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("A123 XYZ"));

        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("A123 XYZ"));

        congestionChargeSystem.vehicleEnteringZone(Vehicle.withRegistration("A123 XYZ"));

        congestionChargeSystem.vehicleLeavingZone(Vehicle.withRegistration("A123 XYZ"));
        congestionChargeSystem.calculateCharges();

    }
}
