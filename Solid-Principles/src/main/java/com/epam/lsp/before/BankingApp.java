package com.epam.lsp.before;

import java.util.Arrays;

public class BankingApp {
    public static void main(String[] args) {
        BankCustomer c1 = new BankCustomer();
        BankCustomer c2 = new BankCustomer();
        BankClerk c3 = new BankClerk();

        for(BankCustomer customer : Arrays.asList(c1, c2, c3)){
            if(customer instanceof BankClerk){
                ((BankClerk) customer).applyExtraDiscountForBankMembers();
            }
            System.out.println(customer.getDiscountPercentage());
        }
    }
}


