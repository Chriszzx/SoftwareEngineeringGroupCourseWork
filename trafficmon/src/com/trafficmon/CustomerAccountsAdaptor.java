package com.trafficmon;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;


/*
    This is adaptor class for handling payment and customer accounts issues which supplied by third-party library.
 */

public class CustomerAccountsAdaptor implements CustomerAccountsService {

    @Override
    public void deductCharge(Vehicle vehicle,BigDecimal charge) throws InsufficientCreditException, AccountNotRegisteredException {

            RegisteredCustomerAccountsService.getInstance().accountFor(vehicle).deduct(charge);


    }
}
