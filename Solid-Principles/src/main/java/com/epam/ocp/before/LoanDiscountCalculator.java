package com.epam.ocp.before;

public class LoanDiscountCalculator {
    public Integer calculateDiscountPercentage(CarLoanProfile profile){
        if(profile.isTrustedCustomer()){
            return 10;
        }
        return 0;
    }
    public Integer calculateDiscountPercentage(PersonalLoanProfile profile){
        if(profile.isTrustedCustomer()){
            return 10;
        }
        return 0;
    }
}


