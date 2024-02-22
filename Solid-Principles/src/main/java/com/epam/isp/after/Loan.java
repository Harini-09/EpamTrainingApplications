package com.epam.isp.after;

import java.util.Random;

public class Loan implements LoanSystem{
    public void dispenseLoan(String accountNumber) {
        // logic to dispense cash to bank account
    }
    public void getDiscountPercentage(String userId) {
        // logic to calculate discount on loan
    }
    public Boolean checkStatus() {
        // logic to get the status whether the amount is credited to bank account
        return new Random().nextBoolean();
    }
}