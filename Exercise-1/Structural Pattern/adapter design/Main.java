import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("adapterLogs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false); 
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error setting up file logging.", e);
        }
    }

    public static void main(String[] args) {
        logger.info("Starting Adapter Pattern Demo...");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                System.out.println("Choose an option:\n1: Use European Socket\n2: Use American Plug (with Adapter)\n0: Exit");
                String choice = scanner.nextLine();

                if ("0".equals(choice)) {
                    running = false;
                    logger.info("Exiting the Adapter Pattern system.");
                } else if ("1".equals(choice)) {
                    EuropeanSocket europeanSocket = new EuropeanSocketImpl();
                    ElectricalDevice europeanDevice = new ElectricalDevice(europeanSocket);
                    europeanDevice.powerOn();
                    logger.info("European device powered on using European socket.");
                } else if ("2".equals(choice)) {
                    AmericanPlug americanPlug = new AmericanPlugImpl();
                    EuropeanSocket adapter = new PlugAdapter(americanPlug);
                    ElectricalDevice adaptedDevice = new ElectricalDevice(adapter);
                    adaptedDevice.powerOn();
                    logger.info("European device powered on using American plug with adapter.");
                } else {
                    logger.warning("Invalid choice entered: " + choice);
                    System.out.println("Invalid choice. Please select 1, 2, or 0 to exit.");
                }
            }
        } catch (Exception e) {
            logger.severe("Unexpected error occurred: " + e.getMessage());
        }

        logger.info("Adapter Pattern Demo has ended.");
    }
}
