package com.epam.lsp.after;

public class BankClerk extends BankCustomer {
    @Override
    public double getDiscountPercentage() {
        this.applyExtraDiscountForBankMembers();
        return discountPercentage;
    }
    public void applyExtraDiscountForBankMembers(){
        // extra discount because he is bank employee
        discountPercentage = discountPercentage * 1.5;
    }
}