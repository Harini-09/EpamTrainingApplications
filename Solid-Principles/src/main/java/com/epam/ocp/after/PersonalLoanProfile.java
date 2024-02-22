package com.epam.ocp.after;

import java.util.Random;

public class PersonalLoanProfile implements LoanProfile  {
    public boolean isTrustedCustomer(){
        // some logic to find customer profile is good or bad based on credit score
        return new Random().nextBoolean();
    }
}
