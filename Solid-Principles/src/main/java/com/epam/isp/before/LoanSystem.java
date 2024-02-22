package com.epam.isp.before;

import java.util.Random;

public class LoanSystem implements PaymentSystem{
    public void creditCash() throws Exception {
        throw new Exception("This operation is not supported in LoanSystem");
    }
    public Boolean checkStatus() {
        // logic to get the status whether the amount is credited to ATM or to bank account
        return new Random().nextBoolean();
    }
    public void dispenseLoan(String accountNumber) {
        // logic to dispense cash to bank account
    }
    public void getDiscountPercentage(String userId) {
        // logic to calculate discount on loan
    }
}