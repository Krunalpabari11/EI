import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PayPalPayment implements PaymentStrategy {
    private String email;
    private static final Logger logger = Logger.getLogger(PayPalPayment.class.getName());
    public PayPalPayment(String email) {
        this.email = email;
        setupLogger();
    }
    private void setupLogger() {
        try {
            FileHandler fileHandler = new FileHandler("allLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting up file logging.", e);
        }
    }
    @Override
    public void pay(int amount) {
        logger.info("Processing PayPal payment of $" + amount);
        System.out.println("Paid $" + amount + " using PayPal account " + email);
    }
}
