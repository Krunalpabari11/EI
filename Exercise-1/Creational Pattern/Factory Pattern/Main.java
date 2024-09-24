import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    static {
        try {
            FileHandler fileHandler = new FileHandler("allLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false); 
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting up file logging.", e);
        }
    }
    public static void main(String[] args) {
        logger.info("Starting Notification Factory Demo...");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("Enter notification type (email, sms, push) or 'exit' to quit: ");
                String userPreference = scanner.nextLine();

                if ("exit".equalsIgnoreCase(userPreference)) {
                    running = false;
                    logger.info("Exiting the Notification Factory system.");
                } else {
                    try {
                        String message = "This is a test notification!";
                        String identifier = "";

                        
                        switch (userPreference.toLowerCase()) {
                            case "email":
                                System.out.println("Enter email address: ");
                                identifier = scanner.nextLine();
                                break;
                            case "sms":
                                System.out.println("Enter phone number: ");
                                identifier = scanner.nextLine();
                                break;
                            case "push":
                                System.out.println("Enter device ID: ");
                                identifier = scanner.nextLine();
                                break;
                            default:
                                throw new IllegalArgumentException("Unknown notification type: " + userPreference);
                        }
                        Notification notification = NotificationFactory.createNotification(userPreference, identifier);
                        notification.send(message);
                        logger.info("Notification sent: " + userPreference);
                    } catch (IllegalArgumentException e) {
                        logger.warning("Error: " + e.getMessage());
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            logger.severe("Unexpected error occurred: " + e.getMessage());
        }

        logger.info("Notification Factory Demo has ended.");
    }
}
