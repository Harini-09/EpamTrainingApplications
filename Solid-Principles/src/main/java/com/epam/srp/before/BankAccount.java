package com.epam.srp.before;

public class BankAccount {
    public void userSignupToGenerateUserId(String firstName, String middleName,
            String lastName, String email, Long mobileNumber) {
        // Logic to generate UserId
        System.out.println("UserId generated successfully");
    }
    public void sendNotification(String email) {
        // complex logic to send notification to email where user
        // can finish his account setup by clicking on the link given in email
        // to set his password
        System.out.println("Notification sent");
    }
    public void createUser(String userId, String password) {
        // logic to save user to Database
        System.out.println("User has been created successfully");
    }
    public void generateAccountNumber(String userId) {
        // Complex logic to generate ACCOUNT_NUMBER for userId generated
        System.out.println("Account number generated successfully");
    }
    public void createUserAccount(String accountNumber, String userId) {
        // logic to save user account details to Database
        System.out.println("User Account has been created successfully");
    }
}