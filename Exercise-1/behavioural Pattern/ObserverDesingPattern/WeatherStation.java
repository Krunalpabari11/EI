import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherStation implements WeatherData {
    private static final Logger logger = Logger.getLogger(WeatherStation.class.getName());
    private List<Observer> observers;
    private float temperature;
    public WeatherStation() {
        this.observers = new ArrayList<>();
        logger.info("WeatherStation initialized with no observers.");
    }
    public void setTemperature(float temperature) {
        if (temperature < -100 || temperature > 100) {  
            logger.warning("Invalid temperature provided.");
            throw new IllegalArgumentException("Temperature out of realistic range.");
        }
        this.temperature = temperature;
        logger.info("Temperature updated to " + temperature + "°C");
        notifyObservers();
    }
    @Override
    public void addObserver(Observer observer) {
        if (observer == null) {
            logger.warning("Attempted to add a null observer.");
            throw new IllegalArgumentException("Observer cannot be null.");
        }
        observers.add(observer);
        logger.info("Observer added. Total observers: " + observers.size());
    }
    @Override
    public void removeObserver(Observer observer) {
        if (!observers.contains(observer)) {
            logger.warning("Observer removal failed, observer not found.");
            throw new IllegalArgumentException("Observer not found in the list.");
        }
        observers.remove(observer);
        logger.info("Observer removed. Total observers: " + observers.size());
    }
    @Override
    public void notifyObservers() {
        if (observers.isEmpty()) {
            logger.warning("No observers to notify.");
        } else {
            for (Observer observer : observers) {
                try {
                    observer.update(temperature);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error notifying observer", e);
                }
            }
        }
    }
}
