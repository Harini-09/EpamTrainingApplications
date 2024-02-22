package com.epam.isp.after;

import java.util.Random;

public class ATM implements ATMSystem {
    public void creditCash() {
        // some logic to perform cash transaction through ATM
    }
    public Boolean checkStatus() {
        // logic to get the status whether the amount is credited to ATM
        return new Random().nextBoolean();
    }
}
