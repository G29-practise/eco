package org.example.eco.security.sms;

public interface SmsNotificationService {
    void sendNotification(String phoneNumber, String message);
}
