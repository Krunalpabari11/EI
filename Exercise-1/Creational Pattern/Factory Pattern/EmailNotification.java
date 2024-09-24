public class EmailNotification implements Notification {
    private String email;

    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
    public void send(String message) {
        System.out.println("Sending Email notification to " + email + ": " + message);
    }
}
