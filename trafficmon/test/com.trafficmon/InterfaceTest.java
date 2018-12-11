package com.trafficmon;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class InterfaceTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    OperationService operationAdaptor = context.mock(OperationService.class);
    CustomerAccountsService accountsService = context.mock(CustomerAccountsService.class);

    private Vehicle vehicle = Vehicle.withRegistration("A123 XYZ");
    private Vehicle vehicle1 = Vehicle.withRegistration("AA");
    private FakeTime faketime = new FakeTime();
    private Eventlog eventlog = new Eventlog();

    @Test
    public void investigationTest()
    {
        eventlog.getInstance().clear();
        NewCongestionChargeSystem system = new NewCongestionChargeSystem();
        system.operationteam = operationAdaptor;
        system.vehicleEnteringZone(vehicle);
        faketime.delayhours(-2);
        system.vehicleLeavingZone(vehicle);
        faketime.resetTime();
        context.checking(new Expectations()
        {{
            exactly(1).of(operationAdaptor).investigate(vehicle);
        }});
        system.newCalculateCharges();

    }

    @Test
    public void penaltyTest()
    {
        eventlog.getInstance().clear();
        NewCongestionChargeSystem system = new NewCongestionChargeSystem();
        system.operationteam = operationAdaptor;
        faketime.setTime(10,0);
        system.vehicleEnteringZone(vehicle1);
        system.vehicleLeavingZone(vehicle1);
        faketime.resetTime();
        context.checking(new Expectations()
        {{
            exactly(1).of(operationAdaptor).penaltyNotice(vehicle1,6);
        }});
        system.newCalculateCharges();
    }

    @Test
    public void deductChargeTest()
    {
        eventlog.getInstance().clear();
        NewCongestionChargeSystem system = new NewCongestionChargeSystem();
        system.customerservice = accountsService;
        faketime.setTime(10,0);
        system.vehicleEnteringZone(vehicle1);
        system.vehicleLeavingZone(vehicle1);
        faketime.resetTime();
        context.checking(new Expectations()
        {{
            try {
                exactly(1).of(accountsService).deductCharge(vehicle1, 6);
            }
            catch (InsufficientCreditException | AccountNotRegisteredException ice)
            {
                system.operationteam.penaltyNotice(vehicle1,6);
            }
        }});
        system.newCalculateCharges();

    }
}
