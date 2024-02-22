package com.epam.isp.before;

public interface PaymentSystem {
    public void creditCash() throws Exception;
    public Boolean checkStatus();
    public void dispenseLoan(String accountNumber) throws Exception;
    public void getDiscountPercentage(String userId) throws Exception;
}
