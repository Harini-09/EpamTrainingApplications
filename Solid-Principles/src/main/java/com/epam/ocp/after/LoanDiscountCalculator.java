package com.epam.ocp.after;

import java.util.Random;

public class LoanDiscountCalculator {
    public Integer calculateDiscountPercentage(LoanProfile profile){
        if(profile.isTrustedCustomer()){
            return 20;
        }
        return 0;
    }
}



