package com.epam.isp.after;

public interface LoanSystem extends Status {
    public void dispenseLoan(String accountNumber);
    public void getDiscountPercentage(String userId);
}
