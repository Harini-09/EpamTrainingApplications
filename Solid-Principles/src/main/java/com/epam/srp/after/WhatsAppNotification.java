package com.epam.srp.after;

public class WhatsAppNotification implements NotificationSender{
    @Override
    public void sendNotification(User user) {
        // logic to send whatsapp notification to user for setting his password for his account
        System.out.println("Notification sent to user's Whatsapp Account");
    }
}
