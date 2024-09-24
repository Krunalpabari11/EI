public class NotificationFactory {
    public static Notification createNotification(String type, String identifier) {
        if (type == null) {
            return null;
        }
        switch (type.toLowerCase()) {
            case "email":
                return new EmailNotification(identifier);
            case "sms":
                return new SMSNotification(identifier);
            case "push":
                return new PushNotification(identifier);
            default:
                throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}
