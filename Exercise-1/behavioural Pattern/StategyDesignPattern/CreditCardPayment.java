import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;
    private static final Logger logger = Logger.getLogger(CreditCardPayment.class.getName());
    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
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
        logger.info("Processing credit card payment of $" + amount);
        System.out.println("Paid $" + amount + " using Credit Card " + cardNumber);
    }
}
