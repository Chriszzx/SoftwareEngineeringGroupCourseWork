package com.trafficmon;

/*
    This is adaptor interface for handling payment and customer accounts issues which supplied by third-party library.
 */

public interface CustomerAccountsService {
    public abstract void deductCharge(Vehicle vehicle, long charge) throws InsufficientCreditException, AccountNotRegisteredException;
}
