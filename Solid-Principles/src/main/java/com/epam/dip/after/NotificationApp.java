package com.epam.dip.after;

public class NotificationApp{
    public static void main(String args[]) {
        NotificationSender sender = new NotificationSender(new WhatsAppNotificationClient());
        sender.sendNotification("");
    }
}