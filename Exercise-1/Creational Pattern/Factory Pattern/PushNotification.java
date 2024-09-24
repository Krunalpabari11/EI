public class PushNotification implements Notification {
    private String deviceId;

    public PushNotification(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void send(String message) {
        System.out.println("Sending Push notification to device " + deviceId + ": " + message);
    }
}
