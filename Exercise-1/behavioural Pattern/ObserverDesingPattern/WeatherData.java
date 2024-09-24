public interface WeatherData {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
