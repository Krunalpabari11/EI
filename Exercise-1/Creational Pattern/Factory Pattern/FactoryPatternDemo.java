// Notification interface
interface Notification {
    void send(String message);
}
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Email notification: " + message);
    }
}
class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS notification: " + message);
    }
}
class PushNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Push notification: " + message);
    }
}
class NotificationFactory {
    public static Notification createNotification(String type) {
        if (type == null) {
            return null;
        }
        switch (type.toLowerCase()) {
            case "email":
                return new EmailNotification();
            case "sms":
                return new SMSNotification();
            case "push":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}
public class FactoryPatternDemo {
    public static void main(String[] args) {
        String userPreference = "email"; 
        Notification notification = NotificationFactory.createNotification(userPreference);
        notification.send("This is a test notification!");
    }
}
