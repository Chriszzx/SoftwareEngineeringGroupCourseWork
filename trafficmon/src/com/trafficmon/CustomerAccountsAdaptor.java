package com.trafficmon;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;

/*
    This is adaptor class for handling payment and customer accounts issues which supplied by third-party library.
 */

public class CustomerAccountsAdaptor implements CustomerAccountsService {

    @Override
    public void deductCharge(Vehicle vehicle, long charge) throws InsufficientCreditException, AccountNotRegisteredException {
        BigDecimal total = new BigDecimal(charge);

            RegisteredCustomerAccountsService.getInstance().accountFor(vehicle).deduct(total);


    }
}
