package com.trafficmon;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;

public class CustomerAccountsAdaptor implements CustomerAccountsService {

    @Override
    public void deductCharge(Vehicle vehicle, int charge) throws InsufficientCreditException, AccountNotRegisteredException {
        BigDecimal total = new BigDecimal(charge);

            RegisteredCustomerAccountsService.getInstance().accountFor(vehicle).deduct(total);


    }
}
