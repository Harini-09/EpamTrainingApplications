package com.epam.lsp.after;

import java.util.Arrays;

public class BankingApp {
    public static void main(String[] args) {
        BankCustomer c1 = new BankCustomer();
        BankCustomer c2 = new BankCustomer();
        BankClerk c3 = new BankClerk();
        for(BankCustomer customer : Arrays.asList(c1, c2, c3)){
            System.out.println(customer.getDiscountPercentage());
        }
    }
}


