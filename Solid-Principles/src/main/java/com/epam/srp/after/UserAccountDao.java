package com.epam.srp.after;

public class UserAccountDao {
    public void createUser(String userId, String password) {
        // logic to save user to Database
        System.out.println("User has been created successfully");
    }
    public void createUserAccount(String accountNumber, String userId) {
        // logic to save user account details to Database
        System.out.println("User Account has been created successfully");
    }
}
