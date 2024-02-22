package com.epam.isp.before;

import java.util.Random;

public class ATMSystem implements PaymentSystem {
    public void creditCash() {
        // some logic to perform cash transaction through ATM
    }
    public Boolean checkStatus() {
        // logic to get the status whether the amount is credited to ATM or to bank account
        return new Random().nextBoolean();
    }
    public void dispenseLoan(String accountNumber) throws Exception {
        throw new Exception("This operation is not supported in ATM System");
    }
    public void getDiscountPercentage(String userId) throws Exception {
        throw new Exception("This operation is not supported in ATM System");
    }
}