package com.epam.lsp.before;

public class BankClerk extends BankCustomer {


    public void applyExtraDiscountForBankMembers(){
        // extra discount because he is bank employee
        discountPercentage = discountPercentage * 1.5;
    }
}
