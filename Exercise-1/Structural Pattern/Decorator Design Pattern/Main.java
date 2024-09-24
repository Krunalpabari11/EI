import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("coffeeLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false); 
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting up file logging.", e);
        }
    }
    public static void main(String[] args) {
        logger.info("Starting Coffee Decorator Demo...");

        try (Scanner scanner = new Scanner(System.in)) {
            Coffee coffee = new SimpleCoffee();
            boolean running = true;
            while (running) {
                System.out.println("\nCurrent Coffee: " + coffee.getDescription());
                System.out.println("Current Cost: $" + coffee.cost());
                System.out.println("Choose an option:\n1: Add Milk ($0.50)\n2: Add Sugar ($0.25)\n3: Add Whipped Cream ($0.75)\n0: Exit");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        coffee = new MilkDecorator(coffee);
                        logger.info("Added Milk.");
                        break;
                    case "2":
                        coffee = new SugarDecorator(coffee);
                        logger.info("Added Sugar.");
                        break;
                    case "3":
                        coffee = new WhippedCreamDecorator(coffee);
                        logger.info("Added Whipped Cream.");
                        break;
                    case "0":
                        running = false;
                        logger.info("Exiting Coffee Decorator system.");
                        break;
                    default:
                        logger.warning("Invalid choice entered: " + choice);
                        System.out.println("Invalid choice. Please select 1, 2, 3, or 0 to exit.");
                        break;
                }
            }
        } catch (Exception e) {
            logger.severe("Unexpected error occurred: " + e.getMessage());
        }

        logger.info("Coffee Decorator Demo has ended.");
    }
}
