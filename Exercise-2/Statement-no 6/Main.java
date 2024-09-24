import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("satelliteCommands.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false); // Suppress console logs
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting up file logging.", e);
        }
    }

    public static void main(String[] args) {
        logger.info("Starting Satellite Command System...");
        
        Satellite satellite = new Satellite();
        CommandInvoker invoker = new CommandInvoker();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Satellite Command System!");
        satellite.printStatus();

        while (true) {
            System.out.println("Enter a command (rotate, activatePanels, deactivatePanels, collectData, status, exit):");
            command = scanner.nextLine();

            try {
                switch (command.toLowerCase()) {
                    case "rotate":
                        System.out.println("Enter direction (North, South, East, West):");
                        String direction = scanner.nextLine();
                        invoker.executeCommand(new RotateCommand(satellite, direction));
                        logger.info("Rotated satellite to " + direction + ".");
                        break;

                    case "activatepanels":
                        invoker.executeCommand(new ActivatePanelsCommand(satellite));
                        logger.info("Activated solar panels.");
                        break;

                    case "deactivatepanels":
                        invoker.executeCommand(new DeactivatePanelsCommand(satellite));
                        logger.info("Deactivated solar panels.");
                        break;

                    case "collectdata":
                        invoker.executeCommand(new CollectDataCommand(satellite));
                        logger.info("Data collection attempted.");
                        break;

                    case "status":
                        satellite.printStatus();
                        break;

                    case "exit":
                        System.out.println("Exiting Satellite Command System.");
                        logger.info("Exited Satellite Command System.");
                        scanner.close();
                        return;

                    default:
                        logger.warning("Unknown command entered: " + command);
                        System.out.println("Unknown command. Please try again.");
                        break;
                }
            } catch (Exception e) {
                logger.severe("Unexpected error occurred: " + e.getMessage());
            }

            satellite.printStatus();
        }
    }
}
