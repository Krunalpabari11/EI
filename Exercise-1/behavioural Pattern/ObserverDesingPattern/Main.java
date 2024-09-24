import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Handler;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("allLogs.log", true); 
            fileHandler.setFormatter(new SimpleFormatter()); 
            logger.addHandler(fileHandler);
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            if (handlers[0] != null) {
                rootLogger.removeHandler(handlers[0]);
            }
            logger.setLevel(Level.ALL); 
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error setting up file logging.", e);
        }
    }

    public static void main(String[] args) {
        logger.info("Starting Weather Station Observer Demo...");
        WeatherStation weatherStation = new WeatherStation();
        PhoneDisplay phoneDisplay = new PhoneDisplay();
        TVDisplay tvDisplay = new TVDisplay();
        WeatherApp weatherApp = new WeatherApp();
        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(tvDisplay);
        weatherStation.addObserver(weatherApp);
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("Enter temperature (or type 'exit' to quit): ");
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    running = false;
                    logger.info("Shutting down the Weather Station system.");
                } else {
                    try {
                        float temp = Float.parseFloat(input);
                        weatherStation.setTemperature(temp);
                        logger.info("Temperature set to " + temp + "°C");
                    } catch (NumberFormatException e) {
                        logger.warning("Invalid temperature input: " + input);
                        System.out.println("Invalid temperature. Please try again.");
                    }
                }
            }
        } catch (Exception e) {
            logger.severe("Unexpected error occurred: " + e.getMessage());
        }

        logger.info("Weather Station Observer Demo has ended.");
    }
}
