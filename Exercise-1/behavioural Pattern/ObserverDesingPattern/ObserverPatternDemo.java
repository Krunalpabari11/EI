import java.util.ArrayList;
import java.util.List;
interface WeatherData {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
interface Observer {
    void update(float temperature);
}
class WeatherStation implements WeatherData {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}
class PhoneDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Phone Display: Current temperature is " + temperature + "°C");
    }
}
class TVDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("TV Display: Current temperature is " + temperature + "°C");
    }
}
class WeatherApp implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Weather App: Current temperature is " + temperature + "°C");
    }
}
public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        PhoneDisplay phoneDisplay = new PhoneDisplay();
        TVDisplay tvDisplay = new TVDisplay();
        WeatherApp weatherApp = new WeatherApp();
        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(tvDisplay);
        weatherStation.addObserver(weatherApp);
        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);
        weatherStation.setTemperature(22.5f);
    }
}
