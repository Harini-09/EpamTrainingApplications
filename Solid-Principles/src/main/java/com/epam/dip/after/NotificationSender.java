package com.epam.dip.after;

public class NotificationSender {
    private NotificationClient notificationClient;
    
    public NotificationSender(NotificationClient notificationClient){
        this.notificationClient = notificationClient;
    }
    public void sendNotification(String userId){
        notificationClient.sendNotification(userId);
    }
}