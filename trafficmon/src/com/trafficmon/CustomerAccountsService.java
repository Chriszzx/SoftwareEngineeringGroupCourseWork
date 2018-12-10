package com.trafficmon;

public interface CustomerAccountsService {
    public abstract void deductCharge(Vehicle vehicle, long charge) throws InsufficientCreditException, AccountNotRegisteredException;
}
