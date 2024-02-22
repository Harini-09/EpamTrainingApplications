package com.epam.dip.before;

public class NotificationSender {
    private MobileNotificationClient mobileNotificationClient;

    public NotificationSender(MobileNotificationClient mobileNotificationClient){
        this.mobileNotificationClient = mobileNotificationClient;
    }
    public void sendNotification(String userId){
        mobileNotificationClient.sendNotification(userId);
    }
}
