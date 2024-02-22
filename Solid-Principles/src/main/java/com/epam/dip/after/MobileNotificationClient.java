package com.epam.dip.after;

public class MobileNotificationClient implements NotificationClient{
    public void sendNotification(String userId) {
        // logic to send notification to bank user/customer for any
        // type of activity performed in his account
        System.out.println("Notification sent to user/customer mobile");
    }
}
