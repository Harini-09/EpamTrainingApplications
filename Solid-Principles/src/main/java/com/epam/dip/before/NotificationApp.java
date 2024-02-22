package com.epam.dip.before;

public class NotificationApp{
    public static void main(String args[]) {
        NotificationSender sender = new NotificationSender(new MobileNotificationClient());
        sender.sendNotification("USR409877");
    }
}


