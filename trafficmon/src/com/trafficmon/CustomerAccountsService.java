package com.trafficmon;

/*
    This is adaptor interface for handling payment and customer accounts issues which supplied by third-party library.
 */

import java.math.BigDecimal;

public interface CustomerAccountsService {
    public abstract void deductCharge(Vehicle vehicle, BigDecimal charge) throws InsufficientCreditException, AccountNotRegisteredException;
}
