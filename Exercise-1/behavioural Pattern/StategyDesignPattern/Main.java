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
        logger.info("Starting Shopping Cart Demo...");
        ShoppingCart cart = new ShoppingCart();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("Choose payment method (1: Credit Card, 2: PayPal, 0: Exit): ");
                String choice = scanner.nextLine();
                if ("0".equals(choice)) {
                    running = false;
                    logger.info("Exiting the Shopping Cart system.");
                } else {
                    try {
                        System.out.println("Enter amount to pay: ");
                        int amount = Integer.parseInt(scanner.nextLine());
                        switch (choice) {
                            case "1":
                                System.out.println("Enter Credit Card Number: ");
                                String cardNumber = scanner.nextLine();
                                System.out.println("Enter Card Holder Name: ");
                                String name = scanner.nextLine();
                                if (cardNumber.isEmpty() || name.isEmpty()) {
                                    logger.warning("Credit card details are incomplete.");
                                    System.out.println("Both card number and card holder name are required.");
                                    break;
                                }
                                cart.setPaymentStrategy(new CreditCardPayment(cardNumber, name));
                                cart.checkout(amount);
                                break;
                            case "2":
                                System.out.println("Enter PayPal Email: ");
                                String email = scanner.nextLine();
                                if (email.isEmpty()) {
                                    logger.warning("PayPal email is missing.");
                                    System.out.println("PayPal email is required.");
                                    break;
                                }
                                cart.setPaymentStrategy(new PayPalPayment(email));
                                cart.checkout(amount);
                                break;
                            default:
                                logger.warning("Invalid payment method choice: " + choice);
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        logger.warning("Invalid amount input.");
                        System.out.println("Invalid amount. Please enter a valid number.");
                    }
                }
            }
        } catch (Exception e) {
            logger.severe("Unexpected error occurred: " + e.getMessage());
        }
        logger.info("Shopping Cart Demo has ended.");
    }
}
