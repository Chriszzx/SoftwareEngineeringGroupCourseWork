package com.trafficmon;

public interface CustomerAccountsService {
    public abstract void deductCharge(Vehicle vehicle, int charge) throws InsufficientCreditException, AccountNotRegisteredException;
}
