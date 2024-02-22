package com.epam.ocp.before;

import java.util.Random;

public class PersonalLoanProfile {
    public boolean isTrustedCustomer(){
        // some logic to find customer profile is good or bad based on credit score
        return new Random().nextBoolean();
    }
}
